package com.example.dmitry.weatherchecker.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ServiceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(ServiceBroadcastReceiver::class.java.simpleName, "Service Stops! Oooooooooooooppppssssss!!!!")
        context.startService(Intent(context, ServiceLauncher::class.java))
    }
}
