package com.samapps.skelet.dataFlow.storage

import com.samapps.skelet.dataFlow.models.FilterModel

interface IUserStorage {
    fun saveFilter(filter:FilterModel?)
    fun getFilter(): FilterModel
    fun saveToken(token: String)
    fun getToken():String
    fun setFirstTimeLoading(firstTime : Boolean)
    fun getFirstTimeLoading() : Boolean
    fun savePhoneNumber(number:String)
    fun getPhoneNumber():String
    fun getEmail(): String?
    fun saveEmail(email:String?)
    fun saveTokenRole(role:String)
    fun getTokenRole():String
    fun setIsConfirmed(confirmed: Boolean)
    fun isConfirmed():Boolean
    fun clearFilter()
    fun setAlphabetic(toBoolean: Boolean)
    fun setTop(toBoolean: Boolean)
    fun setBoxes(toBoolean: Boolean)
    fun setCandles(toBoolean: Boolean)
    fun getAlphabetic(): Boolean
    fun getTop(): Boolean
    fun getBoxes(): Boolean
    fun getCandles(): Boolean
    fun setUserId(deviceId: String)
    fun getUserID(): String
    fun savePublicKey(publicKey: String)
    fun getPublicKey():String
}