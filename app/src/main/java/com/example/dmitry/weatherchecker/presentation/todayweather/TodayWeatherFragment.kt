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
        today_weather_dt.text = event.dt_text
        today_weather_clouds.text = "${event.clouds_all} %"
        today_weather_ground_level.text = event.grnd_level.toString()
        today_weather_sea_level.text = event.sea_level.toString()
        today_weather_city_county.text = event.city_contry
        today_weather_pressure.text = "${event.pressure} gPa"
        today_weather_tempmax.text = event.temp_max.toString()
        today_weather_tempmin.text = event.temp_min.toString()
        today_weather_city_name.text = event.id.toString()
        today_weather_temp.text = event.temp.toString()
        today_weather_description.text = event.weather_description
        today_weather_humidity.text = "${event.humidity} %"
        today_weather_wind_speed.text = "${event.wind_speed} m/s"
        today_weather_icon.setImageResource(this.setIcon(event.weather_icon)!!)
    }

    private fun setIcon(id: String): Int? {
        when (id) {
            "01d" -> return R.drawable.sunday
            "01n" -> return R.drawable.sunnight
            "02d" -> return R.drawable.cloudday
            "02n" -> return R.drawable.cloudnight
            "03d" -> return R.drawable.clearcloudday
            "03n" -> return R.drawable.clearcloudnight
            "04d" -> return R.drawable.darkcloudday
            "04n" -> return R.drawable.darkcloudnight
            "09d" -> return R.drawable.darkcloudrainday
            "09n" -> return R.drawable.darkcloudrainnight
            "10d" -> return R.drawable.cloudrainday
            "10n" -> return R.drawable.cloudrainnight
            "11d" -> return R.drawable.cloudlightday
            "11n" -> return R.drawable.cloudlightnight
            "13d" -> return R.drawable.darkcloudsnowday
            "13n" -> return R.drawable.darkcloudsnownight
            "50d" -> return R.drawable.atmosphereday
            "50n" -> return R.drawable.atmospherenight

        }
        return null
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