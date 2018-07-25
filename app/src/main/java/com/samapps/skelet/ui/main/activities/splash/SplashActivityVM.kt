package com.samapps.skelet.ui.main.activities.splash

import androidx.lifecycle.MutableLiveData
import com.samapps.skelet.AppApplication
import com.samapps.skelet.dataFlow.models.apiModels.RegistrationModel
import com.samapps.skelet.dataFlow.models.responseModel.Response
import com.samapps.skelet.dataFlow.responseModel.Status
import com.samapps.skelet.ui.base.BaseVM
import org.jetbrains.anko.doAsync

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

    fun saveToken(token: String) {
        dataManager.saveToken(token)
    }

    fun getToken() = dataManager.getToken()

    fun saveTokenRole(role: String) = dataManager.saveTokenRole(role)

    fun getRegistrationToken() = reggistrationToken

    fun registrationSubscr(email: String?, instalationId: String) {
        processAsyncProviderCall(
                call = { dataManager.registration(email, instalationId,"","")},
                onSuccess = {

                    doAsync {

                    }
                    reggistrationToken.postValue(Response(Status.SUCCESS, it, null))
                },
                onError = {
                    reggistrationToken.postValue(Response(Status.ERROR,null,it))
                    onError(it)
                }
        )
    }

    fun getEmail(): String? = dataManager.getEmail()

    fun setIsConfirmed(isConfirmed: Boolean) {
        dataManager.setIsConfirmed(isConfirmed)
    }

    fun isConfirmed(): Boolean = dataManager.isConfirmed()

    fun setUserId(deviceId: String) {
        dataManager.setUserId(deviceId)
    }

    fun getUserId() = dataManager.getUserID()

    fun savePublicKey(publicKey: String) {
        dataManager.savePublicKey(publicKey)
    }
}