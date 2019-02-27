package com.example.dmitry.weatherchecker.presentation.vpfragment

import com.arellomobile.mvp.MvpView
import com.example.dmitry.weatherchecker.model.WeatherDataModel

interface VpFragmentView : MvpView {
    fun setAdapter(it: ArrayList<List<WeatherDataModel>>)
    fun showMessage(message: String)
}