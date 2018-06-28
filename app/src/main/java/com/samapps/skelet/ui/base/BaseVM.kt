package com.samapps.skelet.ui.base

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samapps.skelet.AppApplication
import com.samapps.skelet.dataFlow.IDataManager
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject

abstract class BaseVM: ViewModel() {
    @Inject
    lateinit var dataManager: IDataManager
    val progressLiveData = MutableLiveData<Boolean>()
    val alertMessage = MutableLiveData<String?>()
    val favoritesCount = MutableLiveData<Int>()
    val uriLiveData = MutableLiveData<Uri>()

//    protected val registrationProvider by lazy { ProviderInjector.registrationProvider }
//    protected val productProvider = ProviderInjector.productProvider
    protected val tag: String = javaClass.simpleName
    private val coroutines = mutableListOf<Deferred<*>>()
    private val subscriptions = mutableListOf<Job>()

    init {
        AppApplication.component.inject(this)
    }

    protected fun addCoroutine(coroutine: Deferred<*>): Deferred<*> {
        coroutines.add(coroutine)
        return coroutine
    }

    override fun onCleared() {
        coroutines.forEach { it.cancel() }
        coroutines.clear()
        subscriptions.forEach { it.cancel() }
        subscriptions.clear()
        super.onCleared()
    }

    protected fun addSubscription(job: Job): Job {
        subscriptions.add(job)
        return job
    }

    fun showProgress() {
        progressLiveData.postValue(true)
    }

    fun hideProgress() {
        progressLiveData.postValue(false)
    }

//    protected fun <T : Any?> processAsyncProviderCall(call: () -> ProviderResult<T>,
//                                                      onSuccess: (T) -> Unit = { /* nothing by default*/ },
//                                                      onError: (E: Exception?) -> Unit = { /* nothing by default*/ },
//                                                      showProgress: Boolean = false): Deferred<*>
//            = processAsyncProviderCallWithFullResult(call, { it.result?.let { onSuccess(it) } },
//            onError, showProgress)


//    protected fun <T : Any?>
//            processAsyncProviderCallWithFullResult(call: () -> ProviderResult<T>,
//                                                   onSuccess: (ProviderResult<T>) -> Unit = { /* nothing by default*/ },
//                                                   onError: (E: Exception?) -> Unit = { /* nothing by default*/ },
//                                                   showProgress: Boolean = false): Deferred<*> {
//        return addCoroutine(async(UI) {
//            if (showProgress) {
//                showProgress()
//            }
//            val job = async(CommonPool) {
//                call()
//            }
//            with(job.await()) {
//                if (isActive) {
//                    if (isSuccess()) onSuccess(this) else onError(exception)
//                }
//            }
//            if (showProgress) {
//                hideProgress()
//            }
//        })
//    }

    protected fun onError(throwable: Throwable?) {
//        Timber.e("tag $throwable")
        showAlert()
    }





    fun showAlert(text: String? = null) {
        alertMessage.value = text ?: ""
    }



    protected open fun processUriResult(uri: Uri) {
        uriLiveData.value = uri
    }

}