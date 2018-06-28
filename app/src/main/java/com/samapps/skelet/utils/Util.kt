package com.samapps.skelet.utils

import android.os.Bundle
import android.os.Handler
import android.os.Message

/**
 * Created by sergey on 12/5/17.
 */

object Util {
    val MESSAGE_PAYLOAD_KEY = "jeromq-service-payload"

    fun reverseInPlace(input: ByteArray): CharArray {

        val string = CharArray(input.size)
        for (i in input.indices) {
            string[i] = input[i].toChar()
        }

        var i = 0
        var j = string.size - 1
        while (i < string.size / 2) {
            val c = string[i]
            string[i] = string[j]
            string[j] = c
            i++
            j--
        }
        return string
    }

    fun bundledMessage(uiThreadHandler: Handler, msg: String): Message {
        val m = uiThreadHandler.obtainMessage()
        prepareMessage(m, msg)
        return m
    }

    fun prepareMessage(m: Message, msg: String) {
        val b = Bundle()
        b.putString(MESSAGE_PAYLOAD_KEY, msg)
        m.data = b
        return
    }


}
