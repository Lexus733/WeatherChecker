package com.example.dmitry.weatherchecker.api

import com.example.dmitry.weatherchecker.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("forecast")
    fun getWeatherInfoByLocation(@Query("lat") latitude: String,
                          @Query("lon") longitude: String,
                          @Query("cnt") cnt: String,
                          @Query("appid") appid: String) : Call<WeatherData>

    @GET("forecast")
    fun getWeatherInfoByCity(@Query("id") id: Int,
                             @Query("cnt") cnt: Int,
                             @Query("appid") appid: String,
                             @Query("units") units:String,
                             @Query("lang") lang: String) : Call<WeatherData>

}