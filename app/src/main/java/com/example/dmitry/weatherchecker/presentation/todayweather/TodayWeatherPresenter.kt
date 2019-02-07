package com.example.dmitry.weatherchecker.presentation.todayweather

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.model.WeatherData
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.repos.Repos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class TodayWeatherPresenter : MvpPresenter<ITodayWeather>() {
    private val adapter: TodayWeatherAdapter = TodayWeatherAdapter()
    private val repos: Repos = Repos()
    private var daysCount: Int = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        EventBus.getDefault().register(this)
        onFirstAttach()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(it: ArrayList<List<WeatherDataModel>>) {
        refreshAdapter(it[0], it[1])
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

    @SuppressLint("CheckResult")
    private fun getTodayDataFromDb() {
        repos.getTodayDataRx()
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    return@map arrayListOf(it, it)
                }
                .subscribe({
                    createAdapter(it[0], it[1])
                }, {
                    viewState.showMessage("Don't have data in database")
                })
    }

    @SuppressLint("CheckResult")
    private fun onFirstAttach() {
        repos.insertEverythingToDbFromApiRx()
                .subscribeOn(Schedulers.newThread())
                .map {
                    insertDataInDb(it)
                }
                .map {
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
    fun onRefreshScroll() {
        daysCount = 0
        repos.insertEverythingToDbFromApiRx()
                .map {
                    insertDataInDb(it)
                }
                .map {
                    return@map arrayListOf(repos.getTodayData(), repos.getNowData())
                }
                .map {
                    EventBus.getDefault().post(it)
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {
                    viewState.showMessage("Don't have internet connection")
                    getTodayDataFromDb()
                    viewState.setLoadingFalse()
                })
    }

    @SuppressLint("CheckResult")
    fun onSwipeDaysBack() {
        daysCount--
        repos.getForwardDataRX("$daysCount days")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    refreshAdapter(it, it)
                }, {
                    viewState.showMessage("Don't have data in database")
                })
    }

    @SuppressLint("CheckResult")
    fun onSwipeDaysForward() {
        daysCount++
        repos.getForwardDataRX("$daysCount days")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    refreshAdapter(it, it)
                }, {
                    viewState.showMessage("Don't have data in database")
                })
    }

}

