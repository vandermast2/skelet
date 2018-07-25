package com.samapps.skelet.di

import com.samapps.skelet.AppApplication
import com.samapps.skelet.dataFlow.storage.IUserStorage
import com.samapps.skelet.notification.MyFirebaseMessagingService
import com.samapps.skelet.ui.base.BaseVM
import com.samapps.skelet.ui.main.activities.main.MainActivity
import com.samapps.skelet.ui.main.activities.main.MainActivityVM
import com.samapps.skelet.ui.main.fragments.main.underluings.smi.SMIFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(application: AppApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(mainActivityVM: MainActivityVM)

    fun inject(baseVM: BaseVM)

    fun inject(smiFragment: SMIFragment)

    fun inject(iUserStorage: IUserStorage)

    fun inject(myFirebaseMessagingService: MyFirebaseMessagingService)
}