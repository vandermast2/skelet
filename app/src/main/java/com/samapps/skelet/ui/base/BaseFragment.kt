package com.samapps.skelet.ui.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.samapps.skelet.R
import com.samapps.skelet.utils.Constants
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import org.jetbrains.anko.alert
import java.util.concurrent.TimeUnit

abstract class BaseFragment<T : BaseVM> : Fragment() {
    companion object {
        private const val COROUTINE_DELAY = 1L
    }

    abstract val viewModelClass: Class<T>

    protected val viewModel: T by lazy(LazyThreadSafetyMode.NONE) { ViewModelProviders.of(this).get(viewModelClass) }

    private var canBeClicked = true

    private var errorDialog: DialogFragment? = null

    private val coroutines = mutableListOf<Deferred<*>>()

    @Suppress("MemberVisibilityCanPrivate")
    protected fun addCoroutine(coroutine: Deferred<*>) {
        coroutines.add(coroutine)
    }

    protected abstract val layoutId: Int

    protected open fun observeBaseLiveData() = with(viewModel) {

        alertMessage.observe(this@BaseFragment, Observer {
            it?.let {
                this@BaseFragment.showAlert(it)
                alertMessage.value = null
            }
        })
        favoritesCount.observe(this@BaseFragment, Observer {
            it?.let {
//                updateFavoritesCount(it)
            }
        })
        observeLiveData()
    }

    protected abstract val observeLiveData: T.() -> Unit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeBaseLiveData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canBeClicked = true
    }

    override fun onDestroy() {
        coroutines.forEach { it.cancel() }
        coroutines.clear()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        with(viewModel.alertMessage) {
            value?.let {
                this@BaseFragment.showAlert(it)
                value = null
            }
        }
    }


    fun showAlert(text: String?) {
        context?.alert(text!!)
    }

    protected fun processActionWithDelay(delay: Long = Constants.DEFAULT_UI_DELAY,
                                         timeUnit: TimeUnit = TimeUnit.MILLISECONDS,
                                         action: () -> Unit) {
        addCoroutine(async(UI) {
            async(CommonPool) {
                delay(delay, timeUnit)
            }.await()
            if (isActive) {
                action.invoke()
            }
        })
    }

    protected fun <T : Fragment> replaceFragment(fragment: T, @IdRes containerId: Int, needToAddToBackStack: Boolean = true): T {
//        hideKeyboard()
        val name = fragment.javaClass.name
        with(childFragmentManager.beginTransaction()) {
            replace(containerId, fragment, name)
            if (needToAddToBackStack) {
                addToBackStack(name)
            }
            commit()
        }
        childFragmentManager.executePendingTransactions()
        return fragment
    }

    protected fun invokeIfCanAccepted(withDebounce: Boolean = false, invoke: () -> Unit) {
        if (canBeClicked) {
            if (withDebounce) debounceClick()
            invoke()
        }
    }

    // This is something like debounce in rxBinding, but better :)
    private fun debounceClick() {
        addCoroutine(async(UI) {
            canBeClicked = false
            async(CommonPool) {
                delay(COROUTINE_DELAY, TimeUnit.SECONDS)
            }.await()
            if (isActive) {
                canBeClicked = true
            }
        })
    }

    protected fun onBackPressed() = invokeIfCanAccepted { activity?.onBackPressed() }

    open fun onViewPagerSelect() {
        // override it if you want use BaseViewPagerAdapter
    }

    protected fun showAlertDialogCustomView(customView: View,
                                            canelable: Boolean = false,
                                            positiveText: String = getString(R.string.ok),
                                            positiveListener: (dialog: DialogInterface, which: Int) -> Unit
                                            = { _, _ -> },
                                            negativeText: String = getString(R.string.cancel),
                                            negativeListener: (dialog: DialogInterface, which: Int) -> Unit
                                            = { dialog, _ -> dialog.cancel() }) {
        AlertDialog.Builder(context)
                .setView(customView)
                .setCancelable(canelable)
                .setPositiveButton(positiveText, positiveListener)
                .setNegativeButton(negativeText, negativeListener)
                .create()
                .show()
    }

    protected fun tryToOpenUri(uri: String?) {
        try {
//            withNotNull(uri?.toUri()) { inAppCallBack?.checkAndOpenUri(this) }
        } catch (exception: Exception) {
//            Timber.e("parse_uri $exception")
        }
    }


}