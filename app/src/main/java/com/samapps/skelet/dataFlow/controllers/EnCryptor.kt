package com.samapps.skelet.dataFlow.controllers

import android.util.Base64
import com.samapps.skelet.dataFlow.controllers.SecurityController.Companion.CIPHER_TYPE
import java.io.ByteArrayOutputStream
import java.security.KeyStore
import java.security.interfaces.RSAPublicKey
import javax.crypto.Cipher
import javax.crypto.CipherOutputStream


class EnCryptor {
    fun encryptString(keyStore: KeyStore, alias: String, initialText: String): String {
        try {
            val privateKeyEntry = keyStore.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            val publicKey = privateKeyEntry.getCertificate().getPublicKey() as RSAPublicKey

            val inCipher = Cipher.getInstance(CIPHER_TYPE)
            inCipher.init(Cipher.ENCRYPT_MODE, publicKey)

            val outputStream = ByteArrayOutputStream()
            val cipherOutputStream = CipherOutputStream(
                    outputStream, inCipher)
            cipherOutputStream.write(initialText.toByteArray(charset("UTF-8")))
            cipherOutputStream.close()

            val vals = outputStream.toByteArray()
            return Base64.encodeToString(vals, Base64.DEFAULT)
        } catch (e: Exception) {
            return initialText
        }

    }

}