package com.samapps.skelet.di

import com.samapps.skelet.AppApplication
import com.samapps.skelet.dataFlow.IUserStorage
import com.samapps.skelet.ui.base.BaseVM
import com.samapps.skelet.ui.main.activities.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(application: AppApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(baseVM: BaseVM)

    fun inject(iUserStorage: IUserStorage)
}