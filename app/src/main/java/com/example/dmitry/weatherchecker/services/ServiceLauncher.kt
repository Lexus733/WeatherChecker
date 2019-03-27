package com.example.dmitry.weatherchecker.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.dmitry.weatherchecker.repos.Repos

class ServiceLauncher : Service() {
    companion object {
        var listener: (() -> Unit)? = null
    }

    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var repos: Repos

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        repos = Repos()
        repos.insertEverythingToDbFromApi()

        listener = {
            alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            pendingIntent = PendingIntent.getService(this
                    , 0
                    , Intent(this, ServiceForApi::class.java), PendingIntent.FLAG_UPDATE_CURRENT)!!
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP
                    , System.currentTimeMillis() + 3 * 3600000, pendingIntent)
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
