package com.example.dmitry.weatherchecker.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ServiceLauncher : Service() {
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        pendingIntent = PendingIntent.getService(this, 0, Intent(this, ServiceForApi::class.java), 0)!!
        alarmManager.setRepeating(AlarmManager.RTC, 100, 360000, pendingIntent)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC, 100, pendingIntent)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("EXIT", "onDestroy!")
        val broadcastIntent = Intent("uk.ac.shef.oak.ActivityRecognition.RestartIntent")
        sendBroadcast(broadcastIntent)
    }
}
