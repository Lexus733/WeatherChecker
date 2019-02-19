package com.example.dmitry.weatherchecker.presentation.viewpagetodayweather

import com.arellomobile.mvp.MvpView
import com.example.dmitry.weatherchecker.model.WeatherDataModel

interface ITodayWeatherVP : MvpView {
    fun showMessage(message: String)
    fun initView(arrayList: ArrayList<WeatherDataModel>)
    fun setLoadingFalse()
}