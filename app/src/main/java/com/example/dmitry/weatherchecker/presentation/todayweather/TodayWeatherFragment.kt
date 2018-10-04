package com.example.dmitry.weatherchecker.presentation.todayweather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import kotlinx.android.synthetic.main.today_weather_fragment.*
import java.util.*

class TodayWeatherFragment : MvpAppCompatFragment(), ITodayWeather, SwipeRefreshLayout.OnRefreshListener {
    @InjectPresenter
    lateinit var presenter: TodayWeatherPresenter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var powerManager: PowerManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.today_weather_fragment, container, false)
    }

    @SuppressLint("BatteryLife")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        linearLayoutManager.initialPrefetchItemCount = 3
        list_graphs.layoutManager = linearLayoutManager
        powerManager = context!!.getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!powerManager.isIgnoringBatteryOptimizations(context!!.packageName)) {
            val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                    Uri.parse("package:" + activity!!.packageName))
            startActivity(intent)
        }
        swipe_container.setOnRefreshListener(this)
    }

    override fun initData(adapter: TodayWeatherAdapter) {
        list_graphs.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    override fun initView(event: ArrayList<WeatherDataModel>) {
        today_weather_clouds_icon.visibility = View.VISIBLE
        today_weather_humidity_icon.visibility = View.VISIBLE
        today_weather_pressure_icon.visibility = View.VISIBLE
        today_weather_tempmax_icon.visibility = View.VISIBLE
        today_weather_sea_level_icon.visibility = View.VISIBLE
        today_weather_tempmin_icon.visibility = View.VISIBLE
        today_weather_ground_level_icon.visibility = View.VISIBLE
        today_weather_wind_icon.visibility = View.VISIBLE
        today_weather_clouds.text = "${event[0].clouds_all} %"
        today_weather_ground_level.text = event[0].grnd_level.toString()
        today_weather_sea_level.text = event[0].sea_level.toString()
        today_weather_city_county.text = event[0].city_contry
        today_weather_pressure.text = "${event[0].pressure} Па"
        today_weather_tempmax.text = "${event[0].temp_max} °C"
        today_weather_tempmin.text = "${event[0].temp_min} °C"
        today_weather_city_name.text = event[0].city_name
        today_weather_temp.text = "${event[0].temp} °C"
        today_weather_description.text = event[0].weather_description
        today_weather_humidity.text = "${event[0].humidity} %"
        today_weather_wind_speed.text = "${event[0].wind_speed} м/с"
        today_weather_icon.setImageResource(this.setIcon(event[0].weather_icon)!!)
    }

    override fun refreshScroll() {
        presenter.refreshViewAndGetData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.subs()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsub()
    }

    override fun onRefresh() {
        swipe_container.isRefreshing = true
        swipe_container.post {
            refreshScroll()
        }
    }

    override fun setLoadingFalse() {
        swipe_container.isRefreshing = false
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