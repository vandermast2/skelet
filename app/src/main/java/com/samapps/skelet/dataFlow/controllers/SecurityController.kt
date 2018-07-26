package com.samapps.skelet.dataFlow.controllers


import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
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
                val generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    generator.initialize(KeyGenParameterSpec.Builder(
                            "mykey", KeyProperties.PURPOSE_SIGN)
                            .setDigests(KeyProperties.DIGEST_SHA256)
                            .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PSS)
                            .build())
                } else {
                    val spec = KeyPairGeneratorSpec.Builder(context)
                            .setAlias(alias)
                            .setSubject(X500Principal("CN=Sample Name, O=Android Authority"))
                            .setSerialNumber(BigInteger.ONE)
                            .setStartDate(start.time)
                            .setEndDate(end.time)
                            .build()
                    generator.initialize(spec)
                }

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