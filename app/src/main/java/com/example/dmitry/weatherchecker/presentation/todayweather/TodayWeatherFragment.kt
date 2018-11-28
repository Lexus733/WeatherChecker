package com.example.dmitry.weatherchecker.presentation.todayweather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.support.v4.view.GestureDetectorCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import kotlinx.android.synthetic.main.today_weather_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class TodayWeatherFragment : MvpAppCompatFragment(), ITodayWeather, SwipeRefreshLayout.OnRefreshListener {
    @InjectPresenter
    lateinit var presenter: TodayWeatherPresenter
    private lateinit var powerManager: PowerManager
    private lateinit var gestureDetectorCompat: GestureDetectorCompat
    private lateinit var customConstraintLayout: View
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.today_weather_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.date_menu, menu)
        menu!!.findItem(R.id.action_date_text).title = simpleDateFormat.format(Date())
    }

    @SuppressLint("BatteryLife")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gestureDetectorCompat = GestureDetectorCompat(context, inner_constraint_data)
        powerManager = context!!.getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!powerManager.isIgnoringBatteryOptimizations(context!!.packageName)) {
            val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                    Uri.parse("package:" + activity!!.packageName))
            startActivity(intent)
        }
        swipe_container.setOnRefreshListener(this)
        customConstraintLayout = inner_constraint_data
        customConstraintLayout.setOnTouchListener { _, event ->
            gestureDetectorCompat.onTouchEvent(event)
            return@setOnTouchListener true
        }
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
        today_weather_clouds.text = "${event[event.size - 1].clouds_all} %"
        today_weather_ground_level.text = event[event.size - 1].grnd_level.toString()
        today_weather_sea_level.text = event[event.size - 1].sea_level.toString()
        today_weather_city_county.text = event[event.size - 1].city_contry
        today_weather_pressure.text = "${event[event.size - 1].pressure} Па"
        today_weather_tempmax.text = "${event[event.size - 1].temp_max} °C"
        today_weather_tempmin.text = "${event[event.size - 1].temp_min} °C"
        today_weather_city_name.text = event[event.size - 1].city_name
        today_weather_temp.text = "${event[event.size - 1].temp} °C"
        today_weather_description.text = event[event.size - 1].weather_description
        today_weather_humidity.text = "${event[event.size - 1].humidity} %"
        today_weather_wind_speed.text = "${event[event.size - 1].wind_speed} м/с"
        today_weather_icon.setImageResource(this.setIcon(event[event.size - 1].weather_icon)!!)
    }

    override fun initAdapter(adapter: TodayWeatherAdapter) {
        list_graphs.adapter = adapter
    }

    override fun refreshScroll() {
        presenter.refreshViewAndGetData()
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

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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