package com.example.dmitry.weatherchecker.repos

import com.example.dmitry.weatherchecker.model.WeatherDataModel

interface IRepos {
    fun getDataById(id: Int): ArrayList<WeatherDataModel>
    fun getData(): ArrayList<WeatherDataModel>
    fun getLastData(): ArrayList<WeatherDataModel>
    fun insertEverythingToDbFromApi()
    fun getLast10Data(): ArrayList<WeatherDataModel>
}