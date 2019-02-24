package com.example.dmitry.weatherchecker.presentation.mainactivity

import com.arellomobile.mvp.MvpView
import com.example.dmitry.weatherchecker.model.WeatherDataModel

interface MainView : MvpView {
    fun setAdapter(it: ArrayList<List<WeatherDataModel>>)
    fun showMessage(message: String)
}