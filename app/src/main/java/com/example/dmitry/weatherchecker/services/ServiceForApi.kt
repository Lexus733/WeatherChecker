package com.example.dmitry.weatherchecker.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.dmitry.weatherchecker.repos.Repos

class ServiceForApi : Service() {
    private lateinit var repos: Repos

    override fun onCreate() {
        super.onCreate()
        repos = Repos()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread(Runnable { insertDataToDb() }).start()
        return START_REDELIVER_INTENT
    }

    private fun insertDataToDb() {
        repos.insertOneDataToDbFromApi()
        Log.d("TEst", "trying to api")
    }
}
