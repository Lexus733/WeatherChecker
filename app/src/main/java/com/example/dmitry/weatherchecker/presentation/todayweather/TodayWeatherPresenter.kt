package com.example.dmitry.weatherchecker.presentation.todayweather

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class TodayWeatherPresenter : MvpPresenter<ITodayWeather>() {
    private val adapter: TodayWeatherAdapter = TodayWeatherAdapter()
    private val repos: Repos = Repos()
    private var daysCount: Int = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onFirstAttach()
    }

    @SuppressLint("CheckResult")
    private fun onFirstAttach() {
        repos.insertEverythingToDbFromApiRx()
                .subscribeOn(Schedulers.single())
                .map {
                    it.list.map { its ->
                        return@map WeatherDataModel(its.dt,
                                its.main.temp,
                                its.main.temp_min,
                                its.main.temp_max,
                                its.main.pressure,
                                its.main.sea_level,
                                its.main.grnd_level,
                                its.main.humidity,
                                its.main.temp_kf,
                                its.weather[0].main,
                                its.weather[0].description,
                                its.weather[0].icon,
                                its.clouds.all,
                                its.wind.speed,
                                its.wind.deg,
                                its.dt_txt,
                                it.city.name,
                                it.city.country)
                    }
                }
                .map { it ->
                    it.map {
                        repos.insertWeatherDataInDb(it)
                    }
                }.map {
                    return@map arrayListOf(repos.getTodayData(), repos.getNowData())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    createAdapter(it[0], it[1])
                }, {
                    it.printStackTrace()
                    viewState.showMessage("Don't have internet connection")
                    getTodayDataFromDb()
                })
    }

    @SuppressLint("CheckResult")
    fun refreshViewAndGetData() {
        repos.insertEverythingToDbFromApiRx()
                .subscribeOn(Schedulers.single())
                .map {
                    it.list.map { its ->
                        return@map WeatherDataModel(its.dt,
                                its.main.temp,
                                its.main.temp_min,
                                its.main.temp_max,
                                its.main.pressure,
                                its.main.sea_level,
                                its.main.grnd_level,
                                its.main.humidity,
                                its.main.temp_kf,
                                its.weather[0].main,
                                its.weather[0].description,
                                its.weather[0].icon,
                                its.clouds.all,
                                its.wind.speed,
                                its.wind.deg,
                                its.dt_txt,
                                it.city.name,
                                it.city.country)
                    }
                }
                .map { it ->
                    it.map {
                        repos.insertWeatherDataInDb(it)
                    }
                }.map {
                    return@map arrayListOf(repos.getTodayData(), repos.getNowData())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    refreshAdapter(it[0], it[1])
                }, {
                    it.printStackTrace()
                    viewState.showMessage("Don't have internet connection")
                })
    }

    private fun createAdapter(it: List<WeatherDataModel>, it2: List<WeatherDataModel>) {
        adapter.setData(it as ArrayList<WeatherDataModel>)
        viewState.initView(it2 as ArrayList<WeatherDataModel>)
        viewState.initAdapter(adapter)
    }

    private fun refreshAdapter(it: List<WeatherDataModel>, it2: List<WeatherDataModel>) {
        adapter.setData(it as ArrayList<WeatherDataModel>)
        viewState.initView(it2 as ArrayList<WeatherDataModel>)
        viewState.setLoadingFalse()
    }

    @SuppressLint("CheckResult")
    private fun getTodayDataFromDb() {
        repos.getTodayDataRx()
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    return@map arrayListOf(it,it)
                }
                .subscribe({
                    createAdapter(it[0], it[1])
                }, {
                    viewState.showMessage("Don't have data in database")
                })
    }
}

