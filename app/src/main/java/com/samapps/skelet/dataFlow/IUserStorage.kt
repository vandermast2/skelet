package com.samapps.skelet.dataFlow

interface IUserStorage {
    fun saveToken(token: String)
    fun getToken():String
    fun setFirstTimeLoading(firstTime : Boolean)
    fun getFirstTimeLoading() : Boolean
}