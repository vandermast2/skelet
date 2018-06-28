package com.samapps.skelet.dataFlow.network

import io.reactivex.Flowable
import retrofit2.http.GET


interface Api {
    @GET("api")
    fun get():Flowable<String>
}