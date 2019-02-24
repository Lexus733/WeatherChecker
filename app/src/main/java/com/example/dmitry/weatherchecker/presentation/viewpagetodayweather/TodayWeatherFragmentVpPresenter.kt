package com.example.dmitry.weatherchecker.presentation.viewpagetodayweather

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherData
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class TodayWeatherFragmentVpPresenter : MvpPresenter<TodayWeatherVPMain>() {
    private val repos: Repos = Repos()

    @SuppressLint("CheckResult")
    fun onRefreshScroll(days: Int) {
        repos.insertEverythingToDbFromApiRx()
                .subscribeOn(Schedulers.newThread())
                .map {
                    insertDataInDb(it)
                }
                .map {
                    return@map repos.getForwardData("$days days")
                            as ArrayList<WeatherDataModel>
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.initView(it)
                }, {
                    it.printStackTrace()
                    noInternet(days)
                })
    }

    @SuppressLint("CheckResult")
    private fun noInternet(days: Int) {
        repos.getForwardDataRX("$days days")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.initView(it
                            as ArrayList<WeatherDataModel>)
                    viewState.setLoadingFalse()
                }, {
                    it.printStackTrace()
                    viewState.showMessage("Don't have data in databse")
                    viewState.setLoadingFalse()
                })
    }

    private fun insertDataInDb(it: WeatherData) {
        var dataModel: WeatherDataModel
        it.list.map { its ->
            dataModel = WeatherDataModel(its.dt,
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
            repos.insertWeatherDataInDb(dataModel)
        }
    }

}