package com.samapps.skelet.dataFlow.controllers


import android.content.Context
import android.security.KeyPairGeneratorSpec
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.util.*
import javax.inject.Inject
import javax.security.auth.x500.X500Principal


class SecurityController @Inject constructor(private val enCryptor: EnCryptor, private val deCryptor: DeCryptor, val context: Context) {

    private val ALIAS = "com.juliusbaer.premarket"

    companion object {
        val CIPHER_TYPE = "RSA/ECB/PKCS1Padding"
        val CIPHER_PROVIDER = "AndroidOpenSSL"
    }

    private var keyStore: KeyStore? = null

    fun getEncypted(text: String): String {
        return enCryptor.encryptString(keyStore!!, ALIAS, text)
    }

    fun getDecrypted(text: String): String {
        return deCryptor.decryptString(keyStore!!, ALIAS, text)
    }

    fun createNewKeys(alias: String) {
        try {
            // Create new key if needed
            if (!keyStore!!.containsAlias(alias)) {
                val start = Calendar.getInstance()
                val end = Calendar.getInstance()
                end.add(Calendar.YEAR, 1)
                val spec = KeyPairGeneratorSpec.Builder(context)
                        .setAlias(alias)
                        .setSubject(X500Principal("CN=Sample Name, O=Android Authority"))
                        .setSerialNumber(BigInteger.ONE)
                        .setStartDate(start.time)
                        .setEndDate(end.time)
                        .build()
                val generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore")
                generator.initialize(spec)

                val keyPair = generator.generateKeyPair()
            }
        } catch (e: Exception) {

        }

    }

    init {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore!!.load(null)
            createNewKeys(ALIAS)
        } catch (e: Exception) {
        }
    }

}