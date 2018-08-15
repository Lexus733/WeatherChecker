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

    override fun initView(event: WeatherDataModel) {
        today_weather_city_name.text = event.id.toString()
        today_weather_temp.text = event.temp.toString() + " C"
        today_weather_description.text = event.weather_description
        today_weather_humidity.text = event.humidity.toString() + " %"
        today_weather_wind_speed.text = event.wind_speed.toString()+ " m/s"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.subs()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsub()
    }
}