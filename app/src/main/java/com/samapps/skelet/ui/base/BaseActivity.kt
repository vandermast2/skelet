package com.samapps.skelet.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.alert

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
                alert { "ALERT" }
                value = null
            }
        }
    }

    private fun observeBaseLiveData() = with(viewModel) {
        progressLiveData.observe(this@BaseActivity, Observer {
            it?.let { if (it) showProgress() else hideProgress() }
        })
        alertMessage.observe(this@BaseActivity, Observer {
            it?.let {
                alert { "ALERT" }
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
//        hideKeyboard()
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