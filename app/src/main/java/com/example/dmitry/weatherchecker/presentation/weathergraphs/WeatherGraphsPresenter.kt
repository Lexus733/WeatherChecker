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
    private lateinit var handler: android.os.Handler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repos = Repos()
        handler = android.os.Handler()
        list = ArrayList()
        list.addAll(repos.getLast10Data())
    }

    @Subscribe
    fun onEvent(event: ArrayList<WeatherDataModel>) {
        val floatArray = floatArrayOf(event[0].temp.toFloat()
                , event[1].temp.toFloat()
                , event[2].temp.toFloat()
                , event[3].temp.toFloat()
                , event[4].temp.toFloat()
                , event[5].temp.toFloat()
                , event[6].temp.toFloat()
                , event[7].temp.toFloat()
                , event[8].temp.toFloat()
                , event[9].temp.toFloat())
        val dateArray = ArrayList<String>()
        dateArray.add(event[0].dt_text)
        dateArray.add(event[1].dt_text)
        dateArray.add(event[2].dt_text)
        dateArray.add(event[3].dt_text)
        dateArray.add(event[4].dt_text)
        dateArray.add(event[5].dt_text)
        dateArray.add(event[6].dt_text)
        dateArray.add(event[7].dt_text)
        dateArray.add(event[8].dt_text)
        dateArray.add(event[9].dt_text)

        viewState.initView(floatArray)
        handler.postDelayed({ viewState.initLegend(dateArray) }, 2)
    }

    fun subs() = EventBus.getDefault().register(this)

    fun unsub() = EventBus.getDefault().unregister(this)
}