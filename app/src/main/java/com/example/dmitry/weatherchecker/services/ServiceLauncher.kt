package com.example.dmitry.weatherchecker.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.TimeUnit

class ServiceLauncher : Service() {
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            TimeUnit.MINUTES.sleep(30)
            stopSelf()
        }.start()
        alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        pendingIntent = PendingIntent.getService(this, 0, Intent(this, ServiceForApi::class.java), PendingIntent.FLAG_UPDATE_CURRENT)!!
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 1, pendingIntent)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("EXIT", "onDestroy!")
        val broadcastIntent = Intent("uk.ac.shef.oak.ActivityRecognition.RestartIntent")
        sendBroadcast(broadcastIntent)
    }
}
