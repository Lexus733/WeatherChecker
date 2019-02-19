package com.example.dmitry.weatherchecker.presentation.mainactivity

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherData
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MainActivityPresenter : MvpPresenter<IMainActivity>() {
    private val repos: Repos = Repos()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onFirstAttach()
    }

    @SuppressLint("CheckResult")
    private fun onFirstAttach() {
        repos.insertEverythingToDbFromApiRx()
                .subscribeOn(Schedulers.newThread())
                .map {
                    insertDataInDb(it)
                }
                .map {
                    return@map arrayListOf(repos.getForwardData("0 days")
                            , repos.getForwardData("1 days")
                            , repos.getForwardData("2 days")
                            , repos.getForwardData("3 days")
                            , repos.getForwardData("4 days"))
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.setAdapter(it)
                }, {
                    it.printStackTrace()
                    noInternet()
                })
    }

    @SuppressLint("CheckResult")
    private fun noInternet() {
        repos.getForwardDataRX("")
                .subscribeOn(Schedulers.newThread())
                .map {
                    arrayListOf(repos.getForwardData("0 days")
                            , repos.getForwardData("1 days")
                            , repos.getForwardData("2 days")
                            , repos.getForwardData("3 days")
                            , repos.getForwardData("4 days"))
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.setAdapter(it)
                }, {
                    it.printStackTrace()
                    viewState.showMessage("Don't have data in databse")
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