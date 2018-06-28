package com.samapps.skelet.dataFlow

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import com.samapps.skelet.BuildConfig.TOKEN
import javax.inject.Inject


class PreferenceUserStorage @Inject constructor(var context: Context) : IUserStorage {

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
        private const val FIRST_LOADING_KEY = "jvi_first_time_loading"
    }

    fun encrypt(input: String?): String {
        // This is base64 encoding, which is not an encryption
        return Base64.encodeToString(input?.toByteArray(), Base64.DEFAULT)
    }

    fun decrypt(input: String): String {
        return String(Base64.decode(input, Base64.DEFAULT))
    }
}