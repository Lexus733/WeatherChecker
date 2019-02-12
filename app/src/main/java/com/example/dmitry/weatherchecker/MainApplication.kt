package com.example.dmitry.weatherchecker

import android.app.Application
import android.content.Intent
import com.example.dmitry.weatherchecker.database.WeatherDataBase
import com.example.dmitry.weatherchecker.services.ServiceLauncher

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        db = WeatherDataBase.getInstance(this)
        intent = Intent(this, ServiceLauncher::class.java)
        startService(intent)
    }

    companion object {
        private var db: WeatherDataBase? = null
        private var intent: Intent? = null

        fun getIntent() = intent

        fun getDb() = db!!.weatherDataDao()

        fun destroyDb() = WeatherDataBase.destroyInstance()
    }
}