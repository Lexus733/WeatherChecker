package com.example.dmitry.weatherchecker.presentation.viewpagetodayweather

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.customviews.CustomSwipeRefreshLayout
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.presentation.todayweather.TodayWeatherAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TodayWeatherFragmentVP : MvpAppCompatFragment(), SwipeRefreshLayout.OnRefreshListener, ITodayWeatherVP {
    @InjectPresenter
    lateinit var presenter: TodayWeatherFragmentVpPresenter

    private val adapter = TodayWeatherAdapter()
    private var list = ArrayList<WeatherDataModel>()
    private var days: Int = 0
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)
    private lateinit var tempMins: TextView
    private lateinit var cloudsIcon: ImageView
    private lateinit var humidityIcon: ImageView
    private lateinit var pressureIcon: ImageView
    private lateinit var tempMaxIcon: ImageView
    private lateinit var seaLevelIcon: ImageView
    private lateinit var tempMinIcon: ImageView
    private lateinit var grndLevelIcon: ImageView
    private lateinit var windIcon: ImageView
    private lateinit var cloudss: TextView
    private lateinit var groundLevels: TextView
    private lateinit var seaLevels: TextView
    private lateinit var cityCountrys: TextView
    private lateinit var pressures: TextView
    private lateinit var tempMaxs: TextView
    private lateinit var cityNames: TextView
    private lateinit var temps: TextView
    private lateinit var descriptions: TextView
    private lateinit var humiditys: TextView
    private lateinit var windSpeeds: TextView
    private lateinit var weatherIcons: ImageView
    private lateinit var adapterVP: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    companion object {
        fun newInstance(weatherDataModel: List<WeatherDataModel>, p0: Int): TodayWeatherFragmentVP {
            val fragmentVP = TodayWeatherFragmentVP()
            val args = Bundle()
            args.putParcelableArrayList("list", weatherDataModel as ArrayList<WeatherDataModel>)
            args.putInt("days", p0)
            fragmentVP.arguments = args
            return fragmentVP
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun initView(data: ArrayList<WeatherDataModel>) {
        cloudss.text = "${data[0].clouds_all} %"
        groundLevels.text = data[0].grnd_level.toString()
        seaLevels.text = data[0].sea_level.toString()
        cityCountrys.text = data[0].city_contry
        pressures.text = "${data[0].pressure} Па"
        tempMaxs.text = "${data[0].temp_max} °C"
        tempMins.text = "${data[0].temp_min} °C"
        cityNames.text = data[0].city_name
        temps.text = "${data[0].temp} °C"
        descriptions.text = data[0].weather_description
        humiditys.text = "${data[0].humidity} %"
        windSpeeds.text = "${data[0].wind_speed} м/с"
        weatherIcons.setImageResource(this.setIcon(data[0].weather_icon)!!)
        adapter.setData(data)
        setLoadingFalse()
    }

    override fun setLoadingFalse() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = if (arguments != null) arguments!!.getParcelableArrayList("list")
        else ArrayList<WeatherDataModel>()
        days = if (arguments != null) arguments!!.getInt("days") else 0
        setHasOptionsMenu(true)
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = true
        presenter.onRefreshScroll(days)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.date_menu, menu)
        menu!!.findItem(R.id.action_date_text).title = simpleDateFormat.format(Date())
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val result = inflater.inflate(R.layout.view_page_frame_today_weather, container, false)
        cloudsIcon = result.findViewById(R.id.today_weather_clouds_icon) as ImageView
        humidityIcon = result.findViewById(R.id.today_weather_humidity_icon) as ImageView
        pressureIcon = result.findViewById(R.id.today_weather_pressure_icon) as ImageView
        tempMaxIcon = result.findViewById(R.id.today_weather_tempmax_icon) as ImageView
        seaLevelIcon = result.findViewById(R.id.today_weather_sea_level_icon) as ImageView
        tempMinIcon = result.findViewById(R.id.today_weather_tempmin_icon) as ImageView
        grndLevelIcon = result.findViewById(R.id.today_weather_ground_level_icon) as ImageView
        windIcon = result.findViewById(R.id.today_weather_wind_icon) as ImageView
        cloudss = result.findViewById(R.id.today_weather_clouds) as TextView
        groundLevels = result.findViewById(R.id.today_weather_ground_level) as TextView
        seaLevels = result.findViewById(R.id.today_weather_sea_level) as TextView
        cityCountrys = result.findViewById(R.id.today_weather_city_county) as TextView
        pressures = result.findViewById(R.id.today_weather_pressure) as TextView
        tempMaxs = result.findViewById(R.id.today_weather_tempmax) as TextView
        tempMins = result.findViewById(R.id.today_weather_tempmin) as TextView
        cityNames = result.findViewById(R.id.today_weather_city_name) as TextView
        temps = result.findViewById(R.id.today_weather_temp) as TextView
        descriptions = result.findViewById(R.id.today_weather_description) as TextView
        humiditys = result.findViewById(R.id.today_weather_humidity) as TextView
        windSpeeds = result.findViewById(R.id.today_weather_wind_speed) as TextView
        weatherIcons = result.findViewById(R.id.today_weather_icon) as ImageView
        adapterVP = result.findViewById(R.id.list_graphs) as RecyclerView
        swipeRefreshLayout = result.findViewById(R.id.swipe_container) as CustomSwipeRefreshLayout

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
        swipeRefreshLayout.setOnRefreshListener(this)

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