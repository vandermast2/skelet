package com.samapps.skelet

import android.app.Application
import com.facebook.stetho.Stetho
import com.samapps.skelet.di.AppComponent
import com.samapps.skelet.di.AppModule
import com.samapps.skelet.di.DaggerAppComponent
import timber.log.Timber

class AppApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component.inject(this)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) + "::Line:" + element.lineNumber + "::" + element.methodName + "()"
                }
            })
        }

    }



}