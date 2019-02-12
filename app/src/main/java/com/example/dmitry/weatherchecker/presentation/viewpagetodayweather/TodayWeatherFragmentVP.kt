package com.example.dmitry.weatherchecker.presentation.viewpagetodayweather

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.presentation.todayweather.TodayWeatherAdapter

class TodayWeatherFragmentVP : Fragment() {
    private val adapter = TodayWeatherAdapter()
    private var list = ArrayList<WeatherDataModel>()

    companion object {
        fun newInstance(weatherDataModel: List<WeatherDataModel>): TodayWeatherFragmentVP {
            val fragmentVP = TodayWeatherFragmentVP()
            val args = Bundle()
            args.putParcelableArrayList("list", weatherDataModel as ArrayList<WeatherDataModel>)
            fragmentVP.arguments = args
            return fragmentVP
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = if (arguments != null) arguments!!.getParcelableArrayList("list") else ArrayList<WeatherDataModel>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val result = inflater.inflate(R.layout.view_page_frame_today_weather, container, false)
        val cloudsIcon = result.findViewById(R.id.today_weather_clouds_icon) as ImageView
        val humidityIcon = result.findViewById(R.id.today_weather_humidity_icon) as ImageView
        val pressureIcon = result.findViewById(R.id.today_weather_pressure_icon) as ImageView
        val tempMaxIcon = result.findViewById(R.id.today_weather_tempmax_icon) as ImageView
        val seaLevelIcon = result.findViewById(R.id.today_weather_sea_level_icon) as ImageView
        val tempMinIcon = result.findViewById(R.id.today_weather_tempmin_icon) as ImageView
        val grndLevelIcon = result.findViewById(R.id.today_weather_ground_level_icon) as ImageView
        val windIcon = result.findViewById(R.id.today_weather_wind_icon) as ImageView
        val cloudss = result.findViewById(R.id.today_weather_clouds) as TextView
        val groundLevels = result.findViewById(R.id.today_weather_ground_level) as TextView
        val seaLevels = result.findViewById(R.id.today_weather_sea_level) as TextView
        val cityCountrys = result.findViewById(R.id.today_weather_city_county) as TextView
        val pressures = result.findViewById(R.id.today_weather_pressure) as TextView
        val tempMaxs = result.findViewById(R.id.today_weather_tempmax) as TextView
        val tempMins = result.findViewById(R.id.today_weather_tempmin) as TextView
        val cityNames = result.findViewById(R.id.today_weather_city_name) as TextView
        val temps = result.findViewById(R.id.today_weather_temp) as TextView
        val descriptions = result.findViewById(R.id.today_weather_description) as TextView
        val humiditys = result.findViewById(R.id.today_weather_humidity) as TextView
        val windSpeeds = result.findViewById(R.id.today_weather_wind_speed) as TextView
        val weatherIcons = result.findViewById(R.id.today_weather_icon) as ImageView
        val adapterVP = result.findViewById(R.id.list_graphs) as RecyclerView

        cloudsIcon.visibility = View.VISIBLE
        humidityIcon.visibility = View.VISIBLE
        pressureIcon.visibility = View.VISIBLE
        tempMaxIcon.visibility = View.VISIBLE
        seaLevelIcon.visibility = View.VISIBLE
        tempMinIcon.visibility = View.VISIBLE
        grndLevelIcon.visibility = View.VISIBLE
        windIcon.visibility = View.VISIBLE
        cloudss.text = "${list[0].clouds_all} %"
        groundLevels.text = list[0].grnd_level.toString()
        seaLevels.text = list[0].sea_level.toString()
        cityCountrys.text = list[0].city_contry
        pressures.text = "${list[0].pressure} Па"
        tempMaxs.text = "${list[0].temp_max} °C"
        tempMins.text = "${list[0].temp_min} °C"
        cityNames.text = list[0].city_name
        temps.text = "${list[0].temp} °C"
        descriptions.text = list[0].weather_description
        humiditys.text = "${list[0].humidity} %"
        windSpeeds.text = "${list[0].wind_speed} м/с"
        weatherIcons.setImageResource(this.setIcon(list[0].weather_icon)!!)
        adapterVP.adapter = adapter
        adapter.setData(list)
        return result
    }

    private fun setIcon(id: String): Int? {
        when (id) {
            "01d" -> return R.drawable.sun
            "01n" -> return R.drawable.moon
            "02d" -> return R.drawable.cloudsun
            "02n" -> return R.drawable.cloudnight
            "03d" -> return R.drawable.clouds
            "03n" -> return R.drawable.cloudsnight
            "04d" -> return R.drawable.twoclouds
            "04n" -> return R.drawable.twocloudsnight
            "09d" -> return R.drawable.rain
            "09n" -> return R.drawable.rainnight
            "10d" -> return R.drawable.cloudrainsun
            "10n" -> return R.drawable.cloudrainsunnight
            "11d" -> return R.drawable.lightning
            "11n" -> return R.drawable.lightningnight
            "13d" -> return R.drawable.snow
            "13n" -> return R.drawable.snownight
            "50d" -> return R.drawable.dry
            "50n" -> return R.drawable.dry
        }
        return null
    }

}