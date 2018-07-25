package com.samapps.skelet.ui.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.lifecycle.LiveData


abstract class BaseLiveData<T>(val context: Context):LiveData<T>() {
    private var broadcastReceiver: BroadcastReceiver? = null


    private fun prepareReceiver(context: Context) {
        val filter = IntentFilter()
        filter.addAction("android.net.wifi.supplicant.CONNECTION_CHANGE")
        filter.addAction("android.net.wifi.STATE_CHANGE")
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val wifiMgr = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo = wifiMgr.connectionInfo
                val name = wifiInfo.ssid
                if (name.isEmpty()) {
                    setValue(null)
                } else {
                    setValue(name as T)
                }
            }
        }
        context.registerReceiver(broadcastReceiver, filter)
    }

    override fun onActive() {
        prepareReceiver(context)
    }

    override fun onInactive() {
        context.unregisterReceiver(broadcastReceiver)
        broadcastReceiver = null
    }
}