package com.example.dmitry.weatherchecker.repos

import com.example.dmitry.weatherchecker.model.WeatherData
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import io.reactivex.Flowable
import io.reactivex.Single

interface IRepos {
    fun getDataById(id: Int): ArrayList<WeatherDataModel>
    fun getData(): ArrayList<WeatherDataModel>
    fun getLastData(): ArrayList<WeatherDataModel>
    fun insertEverythingToDbFromApi()
    fun insertEverythingToDbFromApiRx(): Single<WeatherData>
    fun getLast10Data(): ArrayList<WeatherDataModel>
    fun getLast10DataRx(): Flowable<List<WeatherDataModel>>
    fun getTodayData(): List<WeatherDataModel>
}