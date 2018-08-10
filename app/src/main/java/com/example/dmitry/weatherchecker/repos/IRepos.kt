package com.example.dmitry.weatherchecker.repos

import com.example.dmitry.weatherchecker.model.WeatherDataModel

interface IRepos {
    fun getOneDataFromApi()
    fun getDataById(id: Int): ArrayList<WeatherDataModel>
    fun getData(): ArrayList<WeatherDataModel>
}