package com.example.dmitry.weatherchecker.repos

import com.example.dmitry.weatherchecker.model.WeatherDataModel

interface IRepos {
    fun insertOneDataToDbFromApi()
    fun getDataById(id: Int): ArrayList<WeatherDataModel>
    fun getData(): ArrayList<WeatherDataModel>
}