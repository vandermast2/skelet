package com.samapps.skelet.dataFlow.storage

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.samapps.skelet.BuildConfig.TOKEN
import com.samapps.skelet.dataFlow.controllers.SecurityController
import com.samapps.skelet.dataFlow.models.FilterModel
import javax.inject.Inject


class PreferenceUserStorage @Inject constructor(var context: Context, val securityController: SecurityController) : IUserStorage {
    override fun saveFilter(filter: FilterModel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFilter(): FilterModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun savePhoneNumber(number: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPhoneNumber(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEmail(): String? = preference.getString(EMAIL_ADRESS, null)

    override fun saveEmail(email: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTokenRole(role: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTokenRole(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setIsConfirmed(confirmed: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isConfirmed(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearFilter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAlphabetic(toBoolean: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTop(toBoolean: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBoxes(toBoolean: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCandles(toBoolean: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlphabetic(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTop(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBoxes(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCandles(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUserId(deviceId: String) {
        preference.edit().putString(USER_ID, securityController.getEncypted(deviceId)).apply()
    }

    override fun getUserID(): String = securityController.getDecrypted(preference.getString(USER_ID, ""))

    override fun savePublicKey(publicKey: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPublicKey(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getToken(): String = decrypt(preference.getString(encrypt(TOKEN), encrypt("")))

    override fun saveToken(token: String) {
        preference.edit().putString(encrypt(TOKEN), encrypt(token)).apply()
    }

    private val preference: SharedPreferences

    init {
        preference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    override fun getFirstTimeLoading(): Boolean = preference.getBoolean(FIRST_LOADING_KEY, true)

    override fun setFirstTimeLoading(firstTime: Boolean) {
        preference.edit().putBoolean(FIRST_LOADING_KEY, firstTime).apply()
    }

    companion object {
        private const val NAME = "jpa_preference_name"
        private const val IS_CONFIRMED = "is_confirmed"
        private const val FIRST_LOADING_KEY = "jvi_first_time_loading"
        private const val PHONE_NUMBER = "phone"
        private const val EMAIL_ADRESS = "email"
        private const val FILTER = "filter"
        private const val TOKEN_ROLE = "token_role"
        private const val USER_ID = "USER_ID"
        private const val PUBLIC_KEY = "PUBLIC_KEY"
    }

    fun encrypt(input: String?): String {
        // This is base64 encoding, which is not an encryption
        return Base64.encodeToString(input?.toByteArray(), Base64.DEFAULT)
    }

    fun decrypt(input: String): String {
        return String(Base64.decode(input, Base64.DEFAULT))
    }
}