package com.example.dmitry.weatherchecker.model

import kotlin.collections.List

data class WeatherData(val cod: String, val message: String, val cnt: Int, val list: List<com.example.dmitry.weatherchecker.model.List> = ArrayList(), val city:City)