package com.example.dmitry.weatherchecker.presentation.viewpagetodayweather

import com.arellomobile.mvp.MvpView
import com.example.dmitry.weatherchecker.model.WeatherDataModel

interface TodayWeatherVPMain : MvpView {
    fun showMessage(message: String)
    fun initView(arrayList: ArrayList<WeatherDataModel>)
    fun setLoadingFalse()
}