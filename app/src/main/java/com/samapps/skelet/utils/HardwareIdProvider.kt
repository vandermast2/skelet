package com.samapps.skelet.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import android.util.Base64
import java.util.*

/**
 * Created by sergey on 12/18/17.
 */
open class HardwareIdProvider(val context: Context) {
    val deviceId: String

    init {
        this.deviceId = UUID.randomUUID().toString()
    }

    @SuppressLint("HardwareIds")
    private fun convert(): String {
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return ""
    }

    private fun getDeviseId(input: String): String {
        if (TextUtils.isEmpty(input)) {
            return DEFAULT_VALUE
        }

        val result = StringBuilder("")
        var i = 0

        while (i < GUID_CHARS_COUNT) {
            if (i == 8 || i == 12 || i == 16 || i == 20) {
                result.append("-")
            }
            if (i < input.length) {
                result.append(input[i])
            } else {
                result.append("0")
            }
            i++
        }

        return result.toString().toUpperCase()
    }

    fun encrypt(input: String): String {
        // This is base64 encoding, which is not an encryption
        return Base64.encodeToString(input.toByteArray(), Base64.DEFAULT)
    }


    companion object {

        private val DEFAULT_VALUE = "00000000-0000-0000-0000-000000000000"
        private val GUID_CHARS_COUNT = 32
    }
}
