package com.example.dmitry.weatherchecker.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class ServiceLauncher : Service() {
   private lateinit var alarmManager: AlarmManager
   private lateinit var pendingIntent : PendingIntent
    override fun onCreate() {
        super.onCreate()
        alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        pendingIntent = PendingIntent.getService(this, 0, Intent(this, ServiceForApi::class.java), 0)!!
        alarmManager.setRepeating(AlarmManager.RTC, 5, 1000, pendingIntent)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}
