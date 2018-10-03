package com.example.dmitry.weatherchecker.presentation.todayweather

import com.arellomobile.mvp.MvpView
import com.example.dmitry.weatherchecker.model.WeatherDataModel

interface ITodayWeather : MvpView {
    fun initView(event: ArrayList<WeatherDataModel>)
    fun initData(adapter: TodayWeatherAdapter)
    fun refreshScroll()
    fun setLoadingFalse()
}