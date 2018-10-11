package com.example.dmitry.weatherchecker.presentation.todayweather

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.MainApplication
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class TodayWeatherPresenter : MvpPresenter<ITodayWeather>() {
    private val adapter: TodayWeatherAdapter = TodayWeatherAdapter()
    private val repos: Repos = Repos()

    init {
        repos.insertEverythingToDbFromApiRx()
        MainApplication.getDb().getLast10DataRx()
                .subscribeOn(Schedulers.newThread())
                .map {
                    val arrayList = ArrayList<WeatherDataModel>()
                    arrayList.addAll(it)
                    return@map arrayList
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.setData(it)
                    viewState.initView(it)
                    viewState.initAdapter(adapter)
                }
    }

    fun refreshViewAndGetData() {
        repos.insertEverythingToDbFromApiRx()
        MainApplication.getDb().getLast10DataRx()
                .subscribeOn(Schedulers.newThread())
                .map {
                    val arrayList = ArrayList<WeatherDataModel>()
                    arrayList.addAll(it)
                    return@map arrayList
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.setData(it)
                    viewState.initView(it)
                    viewState.setLoadingFalse()
                }
    }
}

