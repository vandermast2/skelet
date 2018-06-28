package com.samapps.skelet.di

import android.content.Context
import com.samapps.skelet.dataFlow.IUserStorage
import com.samapps.skelet.dataFlow.PreferenceUserStorage
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
    fun provideUserStorage(context: Context): IUserStorage = PreferenceUserStorage(context = context)
}