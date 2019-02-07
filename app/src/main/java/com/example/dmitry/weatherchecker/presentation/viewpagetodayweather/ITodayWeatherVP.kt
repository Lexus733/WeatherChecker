package com.example.dmitry.weatherchecker.presentation.viewpagetodayweather

import com.arellomobile.mvp.MvpView
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.presentation.todayweather.TodayWeatherAdapter

interface ITodayWeatherVP: MvpView {
    fun initView(event: ArrayList<WeatherDataModel>)
    fun setLoadingFalse()
    fun initAdapter(adapter: TodayWeatherAdapter)
    fun showMessage(message: String)
}