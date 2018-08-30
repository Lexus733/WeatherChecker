package com.example.dmitry.weatherchecker.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.dmitry.weatherchecker.Util.ServiceUtil

class ServiceBroadcastReceiverJob : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        ServiceUtil().scheduleJob(context)
    }
}
