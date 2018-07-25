package com.samapps.skelet.dataFlow.models.responseModel

import com.samapps.skelet.dataFlow.responseModel.Status


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Response<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Response<T> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: Throwable, data: T?): Response<T> {
            return Response(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Response<T> {
            return Response(Status.LOADING, data, null)
        }
    }
}