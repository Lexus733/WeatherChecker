package com.example.dmitry.weatherchecker.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.dmitry.weatherchecker.repos.Repos

class ServiceForApi : Service() {
    private lateinit var repos: Repos
    override fun onCreate() {
        super.onCreate()
        repos = Repos()
        repos.insertEverythingToDbFromApiRx()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ServiceLauncher.listener?.invoke()
        stopSelf()
        return START_STICKY
    }
}
