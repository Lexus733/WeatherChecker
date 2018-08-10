package com.example.dmitry.weatherchecker.presentation.todayweather

import android.content.Context
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.handler.DbWorkerHandler
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.other.ThreadKeys
import com.example.dmitry.weatherchecker.repos.Repos
import java.util.concurrent.TimeUnit

@InjectViewState
class TodayWeatherPresenter(private val context: Context) : MvpPresenter<ITodayWeather>() {
    private var dbWorkerHandler: DbWorkerHandler = DbWorkerHandler(ThreadKeys.DATABASE_THREAD)
    private lateinit var repos: Repos
    private var handler: Handler = Handler()
    private lateinit var list: ArrayList<WeatherDataModel>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        dbWorkerHandler.start()
        repos = Repos(context, dbWorkerHandler)
        list = repos.getData()
        TimeUnit.SECONDS.sleep(2)
        handler.post({ viewState.initView(list) })
    }
}