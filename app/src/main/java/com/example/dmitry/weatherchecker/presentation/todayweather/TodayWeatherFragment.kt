package com.example.dmitry.weatherchecker.presentation.todayweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import kotlinx.android.synthetic.main.today_weather_fragment.*

class TodayWeatherFragment : MvpAppCompatFragment(), ITodayWeather {
    @InjectPresenter
    lateinit var presenter: TodayWeatherPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.today_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView(weatherDataModel: ArrayList<WeatherDataModel>) {
        today_weather_city_name.text = weatherDataModel[0].id.toString()
        today_weather_temp.text = weatherDataModel[0].temp.toString() + " C"
        today_weather_description.text = weatherDataModel[0].weather_description
        today_weather_humidity.text = weatherDataModel[0].humidity.toString() + " %"
        today_weather_wind_speed.text = weatherDataModel[0].wind_speed.toString()+ " m/s"
    }
}