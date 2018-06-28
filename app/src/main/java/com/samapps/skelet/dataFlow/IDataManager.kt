package com.samapps.skelet.dataFlow

interface IDataManager {
    fun tokenSave(token: String)

    fun tokenGet(): String

    fun setFirstTimeLoading()

    fun isFirstTimeLoading(): Boolean
}