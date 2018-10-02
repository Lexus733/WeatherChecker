package com.example.dmitry.weatherchecker.api

import com.example.dmitry.weatherchecker.model.WeatherData
import com.example.dmitry.weatherchecker.other.ApiQueryKeys
import com.google.android.gms.common.api.Api
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET(ApiQueryKeys.FORECAST)
    fun getWeatherInfoByCity(@Query(ApiQueryKeys.ID) id: Int,
                             @Query(ApiQueryKeys.CNT) cnt: Int,
                             @Query(ApiQueryKeys.APPID) appid: String,
                             @Query(ApiQueryKeys.UNITS) units: String,
                             @Query(ApiQueryKeys.LANG) lang: String): Call<WeatherData>

}