package com.example.dmitry.weatherchecker.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.dmitry.weatherchecker.repos.Repos
import java.util.concurrent.TimeUnit

class ServiceForApi : Service() {
    private lateinit var repos: Repos
    private val time: Int = 1

    override fun onCreate() {
        super.onCreate()
        repos = Repos()
        Thread(Runnable { insertDataToDb() })
        Thread(Runnable { while(time == 1){
                TimeUnit.MINUTES.sleep(1)
                insertDataToDb()
            }}).start()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private fun insertDataToDb() {
        repos.insertOneDataToDbFromApi()
    }
}
