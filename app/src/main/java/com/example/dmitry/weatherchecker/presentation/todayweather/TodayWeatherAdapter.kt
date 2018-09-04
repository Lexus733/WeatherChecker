package com.example.dmitry.weatherchecker.presentation.todayweather

import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.squareup.picasso.Picasso

class TodayWeatherAdapter : RecyclerView.Adapter<TodayWeatherAdapter.ViewHolder>() {
    private lateinit var data: ArrayList<WeatherDataModel>
    private lateinit var param: ConstraintLayout.LayoutParams

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.today_weather_item_constraint, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: WeatherDataModel = data[position]
        param = holder.tempTextView.layoutParams as ConstraintLayout.LayoutParams
        getGraphMargin(data.temp.toInt())
        holder.tempTextView.text = "${data.temp} °C"
        holder.tempTextView.setBackgroundColor(getTemperatureColor(data.temp.toInt()))
        holder.timeTextView.text = data.dt_text
        holder.windTextView.text = "${data.wind_speed} m/s"
        Picasso.get()
                .load(this.setIcon(data.weather_icon)!!)
                .placeholder(R.drawable.ic_file_download_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(holder.weatherImageView)
    }

    fun setData(data: ArrayList<WeatherDataModel>) {
        this.data = data
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

    private fun getTemperatureColor(temp: Int): Int {
        return when {
            temp <= 10 -> getColdTemperatureColor(temp)
            temp in 11..15 -> getMediumTemperatureColor(temp)
            temp in 16..20 -> getWarmTemperatureColor(temp)
            else -> getHotTemperatureColor(temp)
        }
    }

    private fun getHotTemperatureColor(temp: Int): Int {
        return if (temp > 40) {
            Color.argb(125, 255, 0, 0)
        } else {
            val green = 255 - (temp - 20) * 12
            Color.argb(125, 255, green, 0)
        }
    }

    private fun getWarmTemperatureColor(temp: Int): Int {
        val red = (temp - 15) * 51
        return Color.argb(125, red, 255, 0)
    }

    private fun getMediumTemperatureColor(temp: Int): Int {
        val blue = 255 - (temp - 10) * 51
        return Color.argb(125, 0, 255, blue)
    }

    private fun getColdTemperatureColor(temp: Int): Int {
        return if (temp < 0) {
            Color.argb(125, 0, 0, 255)
        } else {
            Color.argb(125, 0, 25 * temp, 255)
        }
    }

    private fun getGraphMargin(temp: Int) {
        when {
            temp > 25 -> return param.setMargins(0, 140 - temp * 2, 0, 0)
            temp in 1..25 -> return param.setMargins(0, 70 - temp * 2, 0, 0)
            temp <= 0 -> return param.setMargins(0, 20 - temp * 2, 0, 0)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weatherImageView: ImageView = view.findViewById(R.id.today_weather_item_weather)
        val timeTextView: TextView = view.findViewById(R.id.today_weather_item_time)
        val tempTextView: TextView = view.findViewById(R.id.today_weather_item_temp)
        val windTextView: TextView = view.findViewById(R.id.today_weather_item_wind)
    }
}