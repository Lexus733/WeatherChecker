package com.example.dmitry.weatherchecker.model

import kotlin.collections.List

data class List(val dt: Int, val main: Main, val weather: List<Weather> = ArrayList(), val clouds:Clouds, val wind:Wind, val rain:Rain, val sys: Sys_, val dt_txt: String)