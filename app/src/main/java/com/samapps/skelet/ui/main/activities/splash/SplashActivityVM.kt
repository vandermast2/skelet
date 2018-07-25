package com.samapps.skelet.ui.main.activities.splash

import androidx.lifecycle.MutableLiveData
import com.samapps.skelet.AppApplication
import com.samapps.skelet.dataFlow.models.apiModels.RegistrationModel
import com.samapps.skelet.dataFlow.models.apiModels.RespModel
import com.samapps.skelet.dataFlow.models.responseModel.Response
import com.samapps.skelet.dataFlow.responseModel.Status
import com.samapps.skelet.ui.base.BaseVM

class SplashActivityVM:BaseVM() {

    val reggistrationToken = object : MutableLiveData<Response<RegistrationModel>>() {
        override fun onActive() {
            super.onActive()
            registrationSubscr(getEmail(), getUserId())
        }
    }


    init {
        AppApplication.component.inject(this)
    }

    fun isFirstTime(): Boolean = dataManager.getFirstTimeLoading()

    fun setFirstTimeValue() {
        dataManager.setFirstTimeLoading(true)
    }

    private fun saveToken(token: String) {
        dataManager.saveToken(token)
    }

    fun getToken() = dataManager.getToken()

    private fun saveTokenRole(role: String) = dataManager.saveTokenRole(role)

    fun getRegistrationToken() = reggistrationToken

    fun registrationSubscr(email: String?, instalationId: String) {
        processAsyncProviderCall(
                call = { dataManager.registration(email, instalationId,"","")},
                onSuccess = {
                    reggistrationToken.postValue(Response(Status.SUCCESS,it,null))
                    saveToken(it.accessToken!!)
                    savePublicKey(it.publicKey!!)
                    saveTokenRole(it.role!!)
                    if (it.role == "Subscriber") {
                        setIsConfirmed(true)
                    } else {
                        setIsConfirmed(false)
                    }
                },
                onError = {
                    reggistrationToken.postValue(Response(Status.ERROR,null,it))
                    onError(it)
                }
        )
    }

    fun getEmail(): String? = dataManager.getEmail()

    private fun setIsConfirmed(isConfirmed: Boolean) {
        dataManager.setIsConfirmed(isConfirmed)
    }

    fun isConfirmed(): Boolean = dataManager.isConfirmed()

    fun setUserId(deviceId: String) {
        dataManager.setUserId(deviceId)
    }

    fun getUserId() = dataManager.getUserID()

    private fun savePublicKey(publicKey: String) {
        dataManager.savePublicKey(publicKey)
    }
}