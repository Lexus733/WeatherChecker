package com.example.dmitry.weatherchecker.model

data class WeatherData(val cod: String
                       , val message: String
                       , val cnt: Int
                       , val list: List<com.example.dmitry.weatherchecker.model.Lists>
                       , val city:City)