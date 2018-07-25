package com.samapps.skelet.dataFlow.controllers

import android.util.Log
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

class NodeCrypto {
    private val iv = "fedcba9876543210"//Dummy iv (CHANGE IT!)
    private var ivspec: IvParameterSpec? = null
    private var keyspec: SecretKeySpec? = null
    private var cipher: Cipher? = null

    private val secretKey = "0123456789abcdef"//Dummy secretKey (CHANGE IT!)

    private fun doKey(key: String) {
        var key = key
        ivspec = IvParameterSpec(iv.toByteArray())

        key = padRight(key, 16)

        Log.d("hi", key)

        keyspec = SecretKeySpec(key.toByteArray(), "AES")

        try {
            cipher = Cipher.getInstance("AES/CBC/NoPadding")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        }

    }

    @Throws(Exception::class)
    fun encrypt(text: String?, key: String): ByteArray? {
        if (text == null || text.isEmpty())
            throw Exception("Empty string")

        doKey(key)

        var encrypted: ByteArray? = null

        try {
            cipher!!.init(Cipher.ENCRYPT_MODE, keyspec, ivspec)

            encrypted = cipher!!.doFinal(padString(text).toByteArray())
        } catch (e: Exception) {
            throw Exception("[encrypt] " + e.message)
        }

        return encrypted
    }

    @Throws(Exception::class)
    fun decrypt(code: String?, key: String): ByteArray? {
        if (code == null || code.isEmpty())
            throw Exception("Empty string")

        var decrypted: ByteArray? = null

        doKey(key)

        try {
            cipher!!.init(Cipher.DECRYPT_MODE, keyspec, ivspec)

            decrypted = cipher!!.doFinal(hexToBytes(code))
        } catch (e: Exception) {
            throw Exception("[decrypt] " + e.message)
        }

        return decrypted
    }


    fun bytesToHex(data: ByteArray?): String? {
        if (data == null) {
            return null
        }

        val len = data.size
        var str = ""
        for (i in 0 until len) {
            if (data[i] and 0xFF.toByte() < 16)
                str = str + "0" + java.lang.Integer.toHexString(data[i].toInt() and 0xFF)
            else
                str += java.lang.Integer.toHexString(data[i].toInt() and 0xFF)
        }
        return str
    }


    /**
     *
     */
    private fun hexToBytes(str: String?): ByteArray? {
        return when {
            str == null -> null
            str.length < 2 -> null
            else -> {
                val len = str.length / 2
                val buffer = ByteArray(len)
                for (i in 0 until len) {
                    buffer[i] = Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16).toByte()
                }
                buffer
            }
        }
    }


    private fun padString(source: String): String {
        var source = source
        val paddingChar = ' '
        val size = 16
        val x = source.length % size
        val padLength = size - x

        for (i in 0 until padLength) {
            source += paddingChar
        }

        return source
    }

    /**
     *
     */
    private fun padRight(s: String, n: Int): String {
        return String.format("%1$-" + n + "s", s)
    }

}