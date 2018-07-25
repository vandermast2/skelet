package com.samapps.skelet.di

import android.content.Context
import com.samapps.skelet.dataFlow.controllers.DeCryptor
import com.samapps.skelet.dataFlow.controllers.EnCryptor
import com.samapps.skelet.dataFlow.controllers.SecurityController
import com.samapps.skelet.dataFlow.storage.IUserStorage
import com.samapps.skelet.dataFlow.storage.PreferenceUserStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sergey on 12/19/17.
 */
@Module
class StorageModule {
    @Provides
    @Singleton
    fun provideUserStorage(context: Context, securityController: SecurityController): IUserStorage = PreferenceUserStorage(context = context, securityController = securityController)

    @Provides
    @Singleton
    fun provideEnCryptor(): EnCryptor {
        return EnCryptor()
    }

    @Provides
    @Singleton
    fun provideDeCryptor(): DeCryptor {
        return DeCryptor()
    }

    @Provides
    @Singleton
    fun provideSecurityController(enCryptor: EnCryptor, deCryptor: DeCryptor, context: Context): SecurityController {
        return SecurityController(enCryptor, deCryptor, context)
    }
}