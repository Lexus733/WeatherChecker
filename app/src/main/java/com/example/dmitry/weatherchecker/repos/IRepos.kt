package com.example.dmitry.weatherchecker.repos

import com.example.dmitry.weatherchecker.model.WeatherDataModel
import io.reactivex.Maybe

interface IRepos {
    fun getDataById(id: Int): ArrayList<WeatherDataModel>
    fun getData(): ArrayList<WeatherDataModel>
    fun getLastData(): ArrayList<WeatherDataModel>
    fun insertEverythingToDbFromApi()
    fun insertEverythingToDbFromApiRx()
    fun getLast10Data(): ArrayList<WeatherDataModel>
    fun getLast10DataRx(): Maybe<List<WeatherDataModel>>
}