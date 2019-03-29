package com.example.dmitry.weatherchecker.presentation.viewpagetodayweather

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.other.utils.Utils
import com.example.dmitry.weatherchecker.presentation.todayweather.TodayWeatherAdapter
import kotlinx.android.synthetic.main.view_page_frame_today_weather.view.*

class TodayWeatherFragmentVP : MvpAppCompatFragment(), SwipeRefreshLayout.OnRefreshListener, TodayWeatherVPMain {
    @InjectPresenter
    lateinit var presenter: TodayWeatherFragmentVpPresenter

    private val adapter = TodayWeatherAdapter()
    private var list = ArrayList<WeatherDataModel>()
    private var days: Int = 0
    private var window: Window? = null
    private var actionBar: ActionBar? = null
    private lateinit var tempMins: TextView
    private lateinit var date: TextView
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

    override fun initView(arrayList: ArrayList<WeatherDataModel>) {
        setData(arrayList)
        setLoadingFalse()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun setLoadingFalse() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = if (arguments != null) arguments!!.getParcelableArrayList("list")
        else ArrayList()
        days = if (arguments != null) arguments!!.getInt("days") else 0
        setHasOptionsMenu(true)
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = true
        presenter.onRefreshScroll(days)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.date_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                R.id.action_configuration -> {
                    presenter.onEditPress()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val result = inflater.inflate(R.layout.view_page_frame_today_weather, container, false)
        cloudsIcon = result.today_weather_clouds_icon
        humidityIcon = result.today_weather_humidity_icon
        pressureIcon = result.today_weather_pressure_icon
        tempMaxIcon = result.today_weather_tempmax_icon
        seaLevelIcon = result.today_weather_sea_level_icon
        tempMinIcon = result.today_weather_tempmin_icon
        grndLevelIcon = result.today_weather_ground_level_icon
        windIcon = result.today_weather_wind_icon
        cloudss = result.today_weather_clouds
        groundLevels = result.today_weather_ground_level
        seaLevels = result.today_weather_sea_level
        cityCountrys = result.today_weather_city_county
        pressures = result.today_weather_pressure
        tempMaxs = result.today_weather_tempmax
        tempMins = result.today_weather_tempmin
        cityNames = result.today_weather_city_name
        temps = result.today_weather_temp
        descriptions = result.today_weather_description
        humiditys = result.today_weather_humidity
        windSpeeds = result.today_weather_wind_speed
        weatherIcons = result.today_weather_icon
        adapterVP = result.list_graphs
        date = result.today_weather_date
        swipeRefreshLayout = result.swipe_container
        actionBar = (activity as AppCompatActivity).supportActionBar
        window = (activity as AppCompatActivity).window


        swipeRefreshLayout.setOnRefreshListener(this)

        adapterVP.adapter = adapter
        viewsVisible()
        setData(list)

        return result
    }

    @SuppressLint("SetTextI18n")
    private fun setData(arrayList: ArrayList<WeatherDataModel>) {
        cloudss.text = "${arrayList[0].clouds_all} %"
        groundLevels.text = arrayList[0].grnd_level.toString()
        seaLevels.text = arrayList[0].sea_level.toString()
        cityCountrys.text = arrayList[0].city_contry
        pressures.text = "${arrayList[0].pressure} Па"
        tempMaxs.text = "${arrayList[0].temp_max} °C"
        tempMins.text = "${arrayList[0].temp_min} °C"
        cityNames.text = arrayList[0].city_name
        temps.text = "${arrayList[0].temp} °C"
        descriptions.text = arrayList[0].weather_description
        humiditys.text = "${arrayList[0].humidity} %"
        windSpeeds.text = "${arrayList[0].wind_speed} м/с"
        weatherIcons.setImageResource(Utils.setIcon(arrayList[0].weather_icon)!!)
        date.text = Utils.dateEdit(arrayList[0].dt_text)
        actionBar!!.setBackgroundDrawable(ColorDrawable(Utils.colorByTemp(Math.round(arrayList[0].temp).toInt())))
        Utils.darkenStatusBar(window, Utils.darkenColor(Utils.colorByTemp(Math.round(arrayList[0].temp).toInt())))
        adapter.setData(arrayList)
        swipeRefreshLayout.setColorSchemeColors(Utils.colorByTemp(Math.round(arrayList[0].temp).toInt()))
    }

    private fun viewsVisible() {
        cloudsIcon.visibility = View.VISIBLE
        humidityIcon.visibility = View.VISIBLE
        pressureIcon.visibility = View.VISIBLE
        tempMaxIcon.visibility = View.VISIBLE
        seaLevelIcon.visibility = View.VISIBLE
        tempMinIcon.visibility = View.VISIBLE
        grndLevelIcon.visibility = View.VISIBLE
        windIcon.visibility = View.VISIBLE
    }
}