package com.samapps.skelet.ui.main.fragments.main.underluings.smi

import androidx.lifecycle.MutableLiveData
import com.samapps.skelet.AppApplication
import com.samapps.skelet.dataFlow.models.apiModels.CandleStickModel
import com.samapps.skelet.dataFlow.models.apiModels.Index
import com.samapps.skelet.dataFlow.models.apiModels.JBSMIModel
import com.samapps.skelet.dataFlow.models.responseModel.Response
import com.samapps.skelet.dataFlow.responseModel.Status
import com.samapps.skelet.ui.base.BaseVM


/**
 * Created by sergey on 12/18/17.
 */
class SMIViewModel: BaseVM() {
    private val answer: MutableLiveData<Response<List<JBSMIModel>>> = object : MutableLiveData<Response<List<JBSMIModel>>>() {
        override fun onActive() {
            super.onActive()
            getSMIUnderluing()
        }
    }
    private val answerCandle: MutableLiveData<Response<List<CandleStickModel>>> = object : MutableLiveData<Response<List<CandleStickModel>>>() {
        override fun onActive() {
            super.onActive()
            getSMIUnderluingCandle()
        }
    }

    private val index: MutableLiveData<Response<List<Index>>> = object : MutableLiveData<Response<List<Index>>>() {
        override fun onActive() {
            super.onActive()
            getSmiIndexRequest()
        }
    }

    init {
        AppApplication.component.inject(this)
    }

    fun getSmiIndex()=index
    private fun getSmiIndexRequest(){
        processAsyncProviderCall(
                call = { dataManager.getSmiIndex() },
                onSuccess = {
                    index.postValue(Response(Status.SUCCESS, it, null))
                },
                onError = {
                    index.postValue(Response(Status.ERROR, null, it))
                    onError(it)
                }
        )
    }

    fun getSMIResponse() = answer
    fun getSMIResponseCandle() = answerCandle

    private fun getSMIUnderluing(){
        processAsyncProviderCall(
                call = { dataManager.getSMIUnderlyings() },
                onSuccess = {
                    answer.postValue(Response(Status.SUCCESS, it, null))
                },
                onError = {
                    answer.postValue(Response(Status.ERROR, null, it))
                    onError(it)
                }
        )
    }

    fun setBoxes(toBoolean: Boolean) {
        dataManager.setBoxes(toBoolean)
    }
    fun getTop(): Boolean = dataManager.getTop()
    fun getBoxes(): Boolean = dataManager.getBoxes()

    private fun getSMIUnderluingCandle(){
        processAsyncProviderCall(
                call = { dataManager.getCandleSMI() },
                onSuccess = {
                    answerCandle.postValue(Response(Status.SUCCESS, it, null))
                },
                onError = {
                    answerCandle.postValue(Response(Status.ERROR, null, it))
                    onError(it)
                }
        )
    }
}