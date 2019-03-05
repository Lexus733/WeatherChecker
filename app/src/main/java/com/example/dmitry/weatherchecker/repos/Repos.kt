package com.example.dmitry.weatherchecker.repos

import android.annotation.SuppressLint
import android.util.Log
import com.example.dmitry.weatherchecker.MainApplication
import com.example.dmitry.weatherchecker.api.OpenWeatherApi
import com.example.dmitry.weatherchecker.model.WeatherData
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.other.Utils.Utils
import com.example.dmitry.weatherchecker.other.WeatherApiKeys
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repos : IRepos {
    private lateinit var weatherData: Single<WeatherData>
    private lateinit var retrofit: Retrofit
    private lateinit var retrofitRx: Retrofit
    private lateinit var openWeatherApi: OpenWeatherApi

    override fun getForwardDataRX(days: String): Flowable<List<WeatherDataModel>> {
        return MainApplication.getDb().getForwardDataRX(days = days)
    }

    override fun getForwardData(days: String): List<WeatherDataModel> {
        return MainApplication.getDb().getForwardData(days = days)
    }

    override fun getNowData(): List<WeatherDataModel> {
        return MainApplication.getDb().getNowData()
    }

    override fun getNowDataRx(): Flowable<List<WeatherDataModel>> {
        return MainApplication.getDb().getNowDataRX()
    }

    override fun getTodayDataRx(): Flowable<List<WeatherDataModel>> {
        return MainApplication.getDb().getTodayDataRX()
    }

    override fun getTodayData(): List<WeatherDataModel> {
        return MainApplication.getDb().getTodayData()
    }

    override fun getLast10DataRx(): Flowable<List<WeatherDataModel>> {
        return MainApplication.getDb().getLast10DataRx()
    }

    override fun getLast10Data(): ArrayList<WeatherDataModel> {
        val weatherDataModel = ArrayList<WeatherDataModel>()
        MainApplication.getDb().getLast10Data().let { weatherDataModel.addAll(it) }
        return weatherDataModel
    }

    override fun getLastData(): ArrayList<WeatherDataModel> {
        val weatherDataModel = ArrayList<WeatherDataModel>()
        MainApplication.getDb().getLastData().let { weatherDataModel.addAll(it) }
        return weatherDataModel
    }

    override fun getData(): ArrayList<WeatherDataModel> {
        val weatherDataModel = ArrayList<WeatherDataModel>()
        MainApplication.getDb().getAll().let { weatherDataModel.addAll(it) }
        return weatherDataModel
    }

    override fun getDataById(id: Int): ArrayList<WeatherDataModel> {
        val weatherDataModel = ArrayList<WeatherDataModel>()
        MainApplication.getDb().getOneById(id).let { weatherDataModel.addAll(it) }
        return weatherDataModel
    }

    override fun insertEverythingToDbFromApi() {
        retrofit = Retrofit.Builder().baseUrl(WeatherApiKeys.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        openWeatherApi = retrofit.create(OpenWeatherApi::class.java)
        openWeatherApi.getWeatherInfoByCity(Utils.loadSettings()
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

                        Thread(Runnable {
                            insertWeatherDataInDb(weatherDataModel)
                        }).start()

                    }
                }
            }
        })

    }

    @SuppressLint("CheckResult")
    override fun insertEverythingToDbFromApiRx(): Single<WeatherData> {
        retrofitRx = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(WeatherApiKeys.BASE_URL)
                .build()
        openWeatherApi = retrofitRx.create(OpenWeatherApi::class.java)
        weatherData = openWeatherApi.getWeatherInfoByCityRx(Utils.loadSettings()
                , WeatherApiKeys.CNT
                , WeatherApiKeys.API_KEY
                , WeatherApiKeys.METRIC_UNITS
                , WeatherApiKeys.LANGUAGE)
        return weatherData
    }

    internal fun insertWeatherDataInDb(weatherDataModel: WeatherDataModel) {
        MainApplication.getDb().insert(weatherDataModel)
    }
}

