package com.example.dmitry.weatherchecker.services

import android.app.IntentService
import android.content.Intent
import com.example.dmitry.weatherchecker.repos.Repos

class IntentServiceForApi : IntentService("IntentServiceForApi") {
    private lateinit var repos: Repos
    override fun onCreate() {
        super.onCreate()
        repos = Repos()
    }

    override fun onHandleIntent(intent: Intent?) {
        repos.insertEverythingToDbFromApiRxForService()
        ServiceLauncher.listener?.invoke()
    }
}
