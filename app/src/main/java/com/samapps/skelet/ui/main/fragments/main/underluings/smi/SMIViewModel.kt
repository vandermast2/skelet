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
    private var answer: MutableLiveData<Response<List<JBSMIModel>>> = MutableLiveData()
    private var answerCandle: MutableLiveData<Response<List<CandleStickModel>>> = MutableLiveData()
    private lateinit var index:MutableLiveData<Response<List<Index>>>

    init {
        AppApplication.component.inject(this)
    }

    fun getSmiIndex()=index
    fun getSmiIndexRequest(){
        index = MutableLiveData()

        dataManager.getSmiIndex().subscribe(
                { response -> index.postValue(Response(Status.SUCCESS, response, null)) },
                { error -> index.postValue(Response(Status.ERROR, null, error)) })
    }

    fun getSMIResponse()=answer
    fun getSMIResponseCandle()=answerCandle

    fun getSMIUnderluing(){
        answer = MutableLiveData()
        dataManager.getSMIUnderlyings().subscribe(
                { response -> answer.postValue(Response(Status.SUCCESS, response, null)) },
                { error -> answer.postValue(Response(Status.ERROR, null, error)) })
    }

    fun getSMIUnderluingCandle(){
        answerCandle = MutableLiveData()
        dataManager.getCandleSMI().subscribe(
                { response -> answerCandle.postValue(Response(Status.SUCCESS, response, null)) },
                { error -> answerCandle.postValue(Response(Status.ERROR, null, error)) })
    }
}