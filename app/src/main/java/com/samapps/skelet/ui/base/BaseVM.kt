package com.samapps.skelet.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samapps.skelet.AppApplication
import com.samapps.skelet.dataFlow.managers.IDataManager
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import timber.log.Timber
import javax.inject.Inject

abstract class BaseVM : ViewModel() {
    @Inject
    lateinit var dataManager: IDataManager

    val progressLiveData = MutableLiveData<Boolean>()
    val alertMessage = MutableLiveData<Throwable?>()

    protected val tag: String = javaClass.simpleName
    private val coroutines = mutableListOf<Deferred<*>>()

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
        super.onCleared()
    }


    fun showProgress() {
        progressLiveData.postValue(true)
    }

    fun hideProgress() {
        progressLiveData.postValue(false)
    }

    fun <T : Any?> processAsyncProviderCall(call: () -> Deferred<T>,
                                                    onSuccess: (T) -> Unit = { /* nothing by default*/ },
                                                    onError: (E: Throwable?) -> Unit = { /* nothing by default*/ },
                                                    showProgress: Boolean = false): Deferred<*> = processAsyncProviderCallWithFullResult(call, { onSuccess(it)},
            onError, showProgress)


    private fun <T : Any?>
            processAsyncProviderCallWithFullResult(call: () -> Deferred<T>,
                                                   onSuccess: (T) -> Unit = { /* nothing by default*/ },
                                                   onError: (E: Throwable?) -> Unit = { /* nothing by default*/ },
                                                   showProgress: Boolean = false): Deferred<*> {
        return addCoroutine(async(UI) {
            if (showProgress) {
                showProgress()
            }
            val job = async(CommonPool) {
                call()
            }
            with(job.await()) {
                try {
                    onSuccess(await())
                }catch (t:Throwable){
                    onError(getCompletionExceptionOrNull())
                }
            }
            if (showProgress) {
                hideProgress()
            }
        })
    }

    protected fun onError(throwable: Throwable?) {
        Timber.e("Error $throwable")
        showAlert(throwable)
    }

    fun showAlert(throwable : Throwable? = null) {
        alertMessage.value = throwable
    }
}