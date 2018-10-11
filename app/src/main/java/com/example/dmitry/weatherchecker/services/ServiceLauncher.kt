package com.example.dmitry.weatherchecker.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ServiceLauncher : Service() {

    companion object {
        var listener: (() -> Unit)? = null
    }

    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        listener = {
            alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            pendingIntent = PendingIntent.getService(this, 0, Intent(this, ServiceForApi::class.java), PendingIntent.FLAG_UPDATE_CURRENT)!!
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3 * 3600000, pendingIntent)
        }

        listener?.invoke()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
        Log.i("EXIT", "onDestroy!")
        val broadcastIntent = Intent("uk.ac.shef.oak.ActivityRecognition.RestartIntent")
        sendBroadcast(broadcastIntent)
    }
}
