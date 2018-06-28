package com.samapps.skelet.dataFlow

import com.samapps.skelet.dataFlow.network.Api
import javax.inject.Inject

class DataManger @Inject constructor(private val api: Api, private val storage: IUserStorage) : IDataManager {

    override fun tokenGet(): String = storage.getToken()

    override fun tokenSave(token: String) {
        storage.saveToken(token)
    }

    override fun setFirstTimeLoading() {
        storage.setFirstTimeLoading(false)
    }

    override fun isFirstTimeLoading(): Boolean = storage.getFirstTimeLoading()


}