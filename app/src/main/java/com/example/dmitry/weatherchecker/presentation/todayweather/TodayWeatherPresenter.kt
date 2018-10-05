package com.example.dmitry.weatherchecker.presentation.todayweather

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.repos.Repos

@InjectViewState
class TodayWeatherPresenter : MvpPresenter<ITodayWeather>() {
    private val adapter: TodayWeatherAdapter = TodayWeatherAdapter()
    private val repos: Repos = Repos()
    private val handler: Handler = Handler()

    init {
        Thread(Runnable {
            var list = repos.getLast10Data()
            handler.post {
                adapter.setData(list)
                viewState.initView(list)
                viewState.initAdapter(adapter)
            }
        }).start()
    }

    fun refreshViewAndGetData() {
        repos.insertEverythingToDbFromApi()
        Thread(Runnable {
            var list = repos.getLast10Data()
            handler.post {
                adapter.setData(list)
                viewState.initView(list)
                viewState.setLoadingFalse()
            }
        }).start()
    }
}

