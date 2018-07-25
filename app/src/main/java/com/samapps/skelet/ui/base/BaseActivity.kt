package com.samapps.skelet.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.samapps.skelet.utils.UiUtils.hideKeyboard
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import retrofit2.HttpException
import timber.log.Timber
import java.net.HttpURLConnection

abstract class BaseActivity<T : BaseVM> : AppCompatActivity() {
    abstract val viewModelClass: Class<T>
    protected val viewModel: T by lazy(LazyThreadSafetyMode.NONE) { ViewModelProviders.of(this).get(viewModelClass) }
    protected abstract val layoutId: Int
    protected abstract val containerId: Int
    protected abstract val observeLiveData: T.() -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        observeBaseLiveData()
    }

    override fun onResume() {
        super.onResume()
        with(viewModel.alertMessage) {
            value?.let {
                parseError(it)
                value = null
            }
        }
    }

    fun parseError(it: Throwable) {
        if (it is HttpException) {
            when ((it).code()) {
                HttpURLConnection.HTTP_BAD_REQUEST -> toast("Error: ${it.message()}")
//                HttpURLConnection.HTTP_UNAUTHORIZED -> startActivity(Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
//                HttpURLConnection.HTTP_FORBIDDEN -> startActivity(Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
//                HttpURLConnection.HTTP_NOT_FOUND -> ServerErrorDialog().showDialog(this)
//                HttpURLConnection.HTTP_INTERNAL_ERROR -> ServerErrorDialog().showDialog(this)
//                else -> ServerErrorDialog().showDialog(this)
            }
        } else {
            Timber.e("Error: ${it}")
//            ServerErrorDialog().showDialog(this)
        }
    }

    private fun observeBaseLiveData() = with(viewModel) {
        progressLiveData.observe(this@BaseActivity, Observer {
            it?.let { if (it) showProgress() else hideProgress() }
        })
        alertMessage.observe(this@BaseActivity, Observer {
            it?.let {
                parseError(it)
                alertMessage.value = null
            }
        })

        observeLiveData()
    }

    /**
     * Replace fragment with tag equals to it's class name {@link Class#getName()}
     *
     * @param fragment             Instance of {@link Fragment}
     * @param needToAddToBackStack boolean value representing necessity for adding fragment to backstack.
     *                             If true fragment will be added to backstack with tag equals
     *                             to it's class name }
     */
    protected fun <T : Fragment> replaceFragment(fragment: T, needToAddToBackStack: Boolean = true): T {
        hideKeyboard(currentFocus)
        val name = fragment.javaClass.name
        with(supportFragmentManager.beginTransaction()) {
            replace(containerId, fragment, name)
            if (needToAddToBackStack) {
                addToBackStack(name)
            }
            commit()
        }
        supportFragmentManager.executePendingTransactions()
        return fragment
    }






}