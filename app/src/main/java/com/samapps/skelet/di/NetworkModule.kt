package com.samapps.skelet.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.samapps.skelet.BuildConfig
import com.samapps.skelet.dataFlow.IUserStorage
import com.samapps.skelet.dataFlow.network.Api
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [StorageModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): Api
            = retrofit.create(Api::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(httpUrl: HttpUrl,
                        client: OkHttpClient,
                        gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
    }

    @Singleton
    @Provides
    fun provideHttpUrl():HttpUrl = HttpUrl.parse(BuildConfig.API_BASE_URL)!!

    @Singleton
    @Provides
    fun provideOkHttpClient(loggerInterceptor: HttpLoggingInterceptor,storage: IUserStorage): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val request = original.newBuilder()
                        .header("Authorization", "Bearer ${storage.getToken()}")
                        .method(original.method(), original.body())
                        .build()
                it.proceed(request)
            }
//            .authenticator({ route: Route, response: Response -> })
            .addNetworkInterceptor(loggerInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
//            Timber.tag(NETWORK_TAG).d(it)
        })
        logger.level = HttpLoggingInterceptor.Level.BODY

        return logger
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
            .serializeNulls()
            .create()

    companion object {
        private const val NETWORK_TAG = "Network"
    }
}