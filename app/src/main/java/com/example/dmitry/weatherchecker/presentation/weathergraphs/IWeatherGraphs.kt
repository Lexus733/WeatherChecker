package com.example.dmitry.weatherchecker.presentation.weathergraphs

import com.arellomobile.mvp.MvpView

interface IWeatherGraphs : MvpView {
    fun initView(floatArray: FloatArray)
}