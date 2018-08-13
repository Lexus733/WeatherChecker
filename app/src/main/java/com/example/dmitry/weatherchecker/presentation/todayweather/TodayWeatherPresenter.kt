package com.example.dmitry.weatherchecker.presentation.todayweather

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

@InjectViewState
class TodayWeatherPresenter : MvpPresenter<ITodayWeather>() {
    private lateinit var repos: Repos
    private var handler: Handler = Handler()
    private lateinit var list: ArrayList<WeatherDataModel>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repos = Repos()
        doAsync {
            list = repos.getData()
            uiThread {
                handler.post({ viewState.initView(list) })
            }
        }
    }
}

