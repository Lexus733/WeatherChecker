package com.example.dmitry.weatherchecker

import android.app.Application
import com.example.dmitry.weatherchecker.database.WeatherDataBase
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        cicerone = Cicerone.create()
    }

    companion object {
        private var cicerone: Cicerone<Router>? = null

        fun getNavigatorHolder(): NavigatorHolder =
                requireNotNull(cicerone, { "Parameter 'cicerone' is missing!" }).navigatorHolder

        fun getRouter(): Router =
                requireNotNull(cicerone, { "Parameter 'cicerone' is missing!" }).router
    }
}