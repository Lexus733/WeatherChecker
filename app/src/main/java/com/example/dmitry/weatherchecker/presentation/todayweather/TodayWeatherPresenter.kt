package com.example.dmitry.weatherchecker.presentation.todayweather

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

@InjectViewState
class TodayWeatherPresenter : MvpPresenter<ITodayWeather>() {
    private lateinit var adapter: TodayWeatherAdapter
    private lateinit var repos: Repos
    private lateinit var handler: Handler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        refreshView()
    }

    @Subscribe
    fun onEvent(event: ArrayList<WeatherDataModel>) {
        handler.post { viewState.initView(event) }
    }

    fun refreshView() {
        repos = Repos()
        adapter = TodayWeatherAdapter()
        handler = Handler()
        doAsync {
            adapter.setData(repos.getLast10Data())
            uiThread {
                viewState.initData(adapter)
            }
        }
    }

    fun subs() = EventBus.getDefault().register(this)

    fun unsub() = EventBus.getDefault().unregister(this)
}

