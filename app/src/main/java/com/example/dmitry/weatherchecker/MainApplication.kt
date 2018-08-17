package com.example.dmitry.weatherchecker

import android.app.Application
import android.content.Intent
import com.example.dmitry.weatherchecker.database.WeatherDataBase
import com.example.dmitry.weatherchecker.services.ServiceLauncher
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        cicerone = Cicerone.create()
        db = WeatherDataBase.getInstance(this)
        intent = Intent(this,ServiceLauncher::class.java)
        startService(intent)
    }

    companion object {
        private var cicerone: Cicerone<Router>? = null
        private var db: WeatherDataBase? = null
        private var intent: Intent? = null

        fun getIntent() = intent

        fun getDb() = db!!.weatherDataDao()

        fun destroyDb() = WeatherDataBase.destroyInstance()

        fun getNavigatorHolder(): NavigatorHolder =
                requireNotNull(cicerone, { "Parameter 'cicerone' is missing!" }).navigatorHolder

        fun getRouter(): Router =
                requireNotNull(cicerone, { "Parameter 'cicerone' is missing!" }).router
    }
}