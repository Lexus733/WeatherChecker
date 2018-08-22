package com.example.dmitry.weatherchecker.presentation.weathergraphs

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@InjectViewState
class WeatherGraphsPresenter : MvpPresenter<IWeatherGraphs>() {
    private val repos: Repos = Repos()
    private var dataArray = ArrayList<WeatherDataModel>()

    init {
        dataArray.addAll(repos.getLast10Data())
    }

    @Subscribe
    fun onEvent(event: ArrayList<WeatherDataModel>) {
        var floatArray = floatArrayOf(event[0].temp.toFloat()
                , event[1].temp.toFloat()
                , event[2].temp.toFloat()
                , event[3].temp.toFloat()
                , event[4].temp.toFloat()
                , event[5].temp.toFloat()
                , event[6].temp.toFloat()
                , event[7].temp.toFloat()
                , event[8].temp.toFloat()
                , event[9].temp.toFloat())
        var floatArray2 = floatArrayOf(100F,200F,300F,150F,-100F)
        viewState.initview(floatArray2)
    }

    fun subs() = EventBus.getDefault().register(this)

    fun unsub() = EventBus.getDefault().unregister(this)
}