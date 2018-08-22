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
    private lateinit var repos: Repos
    private lateinit var handler: Handler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repos = Repos()
        handler = Handler()
        repos.getLastData()
    }

    @Subscribe
    fun onEvent(event: WeatherDataModel) {
       handler.post { viewState.initView(event) }
    }

    fun subs() = EventBus.getDefault().register(this)

    fun unsub() = EventBus.getDefault().unregister(this)
}

