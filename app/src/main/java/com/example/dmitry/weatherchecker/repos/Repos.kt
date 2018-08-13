package com.example.dmitry.weatherchecker.repos

import android.util.Log
import com.example.dmitry.weatherchecker.MainApplication
import com.example.dmitry.weatherchecker.api.OpenWeatherApi
import com.example.dmitry.weatherchecker.model.WeatherData
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.other.WeatherApiKeys
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repos() : IRepos {
    private lateinit var retrofit: Retrofit
    private lateinit var openWeatherApi: OpenWeatherApi

    override fun getData(): ArrayList<WeatherDataModel> {
        val weatherDataModel = ArrayList<WeatherDataModel>()
        Thread(Runnable {
            weatherDataModel.addAll(MainApplication.getDb().getAll())
        }).start()
        return weatherDataModel
    }

    override fun getDataById(id: Int): ArrayList<WeatherDataModel> {
        val weatherDataModel = ArrayList<WeatherDataModel>()
        Thread(Runnable {
            weatherDataModel.addAll(MainApplication.getDb().getOne(id))
        }).start()
        return weatherDataModel
    }

    override fun insertOneDataToDbFromApi() {
        retrofit = Retrofit.Builder().baseUrl(WeatherApiKeys.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        openWeatherApi = retrofit.create(OpenWeatherApi::class.java)
        openWeatherApi.getWeatherInfoByCity(WeatherApiKeys.CITY_ID
                , WeatherApiKeys.CNT
                , WeatherApiKeys.API_KEY
                , WeatherApiKeys.METRIC_UNITS
                , WeatherApiKeys.LANGUAGE).enqueue(object : Callback<WeatherData> {
            override fun onFailure(call: Call<WeatherData>?, t: Throwable?) {
                Log.d("Error!", "$t")
            }

            override fun onResponse(call: Call<WeatherData>?, response: Response<WeatherData>?) {
                response?.let {
                    response.body()!!.list.map {
                        val weatherDataModel = WeatherDataModel(it.dt,
                                it.main.temp,
                                it.main.temp_min,
                                it.main.temp_max,
                                it.main.pressure,
                                it.main.sea_level,
                                it.main.grnd_level,
                                it.main.humidity,
                                it.main.temp_kf,
                                it.weather[0].main,
                                it.weather[0].description,
                                it.weather[0].icon,
                                it.clouds.all,
                                it.wind.speed,
                                it.wind.deg,
                                it.dt_txt,
                                response.body()!!.city.name,
                                response.body()!!.city.country)
                        insertWeatherDataInDb(weatherDataModel)
                    }
                }
            }
        })
    }

    private fun insertWeatherDataInDb(weatherDataModel: WeatherDataModel) =
            Thread(Runnable { MainApplication.getDb().insert(weatherDataModel) }).start()

    private fun deleteWeatherDataInDb(id: Int) =
            Thread(Runnable { MainApplication.getDb().deleteOne(id) }).start()

    private fun destroyHandlerAndInstance() = MainApplication.destroyDb()
}