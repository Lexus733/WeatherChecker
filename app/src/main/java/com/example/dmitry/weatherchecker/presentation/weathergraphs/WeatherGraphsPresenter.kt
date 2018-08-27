package com.example.dmitry.weatherchecker.presentation.weathergraphs

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

@InjectViewState
class WeatherGraphsPresenter : MvpPresenter<IWeatherGraphs>() {
    private lateinit var repos: Repos
    private lateinit var list: ArrayList<WeatherDataModel>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repos = Repos()
        list = ArrayList()
        list.addAll(repos.getLast10Data())
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

        viewState.initView(floatArray)
    }

    fun subs() = EventBus.getDefault().register(this)

    fun unsub() = EventBus.getDefault().unregister(this)
}