package com.samapps.skelet.ui.main.fragments.main.underluings.companyFragments.company

import androidx.lifecycle.MutableLiveData
import com.samapps.skelet.dataFlow.models.apiModels.Index
import com.samapps.skelet.dataFlow.models.apiModels.UnderlyingById
import com.samapps.skelet.dataFlow.models.responseModel.Response
import com.samapps.skelet.dataFlow.responseModel.Status
import com.samapps.skelet.ui.base.BaseVM

class CompanyVM:BaseVM() {
    private lateinit var underluingResult: MutableLiveData<Response<UnderlyingById>>
    private lateinit var index: MutableLiveData<Response<Index>>

    fun getUnderluingResult() = underluingResult
    fun getItem(id: String?) {
        underluingResult = MutableLiveData()
        dataManager.getUnderlyingsById(id).subscribe({ response -> underluingResult.postValue(Response(Status.SUCCESS, response, null)) },
                { error -> underluingResult.postValue(Response(Status.ERROR, null, error)) })
    }

    fun getIndex()=index
    fun getIndexRequest(id: String) {
        index = MutableLiveData()
        dataManager.getIndex(id).subscribe(
                { response -> index.postValue(Response(Status.SUCCESS, response, null)) },
                { error -> index.postValue(Response(Status.ERROR, null, error)) })
    }
}