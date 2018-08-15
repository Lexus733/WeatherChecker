package com.example.dmitry.weatherchecker.presentation.todayweather

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@InjectViewState
class TodayWeatherPresenter : MvpPresenter<ITodayWeather>() {
    private lateinit var repos: Repos
    private lateinit var list: ArrayList<WeatherDataModel>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repos = Repos()
        list = repos.getLastData()
    }

    @Subscribe
    fun onEvent(event: WeatherDataModel) {
        viewState.initView(event)
    }

    fun subs() = EventBus.getDefault().register(this)

    fun unsub() = EventBus.getDefault().unregister(this)
}

