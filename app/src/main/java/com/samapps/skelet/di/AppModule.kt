package com.samapps.skelet.di

import android.app.Application
import android.content.Context
import com.samapps.skelet.dataFlow.managers.DataManger
import com.samapps.skelet.dataFlow.managers.IDataManager
import com.samapps.skelet.dataFlow.storage.IUserStorage
import com.samapps.skelet.dataFlow.network.Api
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [NetworkModule::class, StorageModule::class])
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideDataManager(api: Api, storageModule: IUserStorage): IDataManager {
        return DataManger(api, storageModule)
    }

}