package com.example.dmitry.weatherchecker.presentation.todayweather

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@InjectViewState
class TodayWeatherPresenter : MvpPresenter<ITodayWeather>() {
    private val adapter: TodayWeatherAdapter = TodayWeatherAdapter()
    private val repos: Repos = Repos()
    private val handler: Handler = Handler()

    init {
        adapter.setData(repos.getLast10Data())
    }

    @Subscribe
    fun onEvent(event: ArrayList<WeatherDataModel>) {
        handler.post {
            viewState.initView(event, adapter)
        }
    }

    fun refreshViewAndGetData() {
        repos.insertEverythingToDbFromApi()
        adapter.setData(repos.getLast10Data())
        viewState.setLoadingFalse()
    }

    fun subs() = EventBus.getDefault().register(this)

    fun unsub() = EventBus.getDefault().unregister(this)
}

