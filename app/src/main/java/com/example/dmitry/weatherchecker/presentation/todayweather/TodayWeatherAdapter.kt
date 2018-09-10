package com.example.dmitry.weatherchecker.presentation.todayweather

import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.squareup.picasso.Picasso
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class TodayWeatherAdapter : RecyclerView.Adapter<TodayWeatherAdapter.ViewHolder>() {
    private val dataTest = arrayListOf<Int>(50, 40, 30, 20, 10, 0, -10, -20, -30, -40)
    private lateinit var data: ArrayList<WeatherDataModel>
    private lateinit var param: ConstraintLayout.LayoutParams
    private val datePattern = Pattern
            .compile("^(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})$")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.today_weather_item_constraint, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: WeatherDataModel = data[position]
        param = holder.tempTextView.layoutParams as ConstraintLayout.LayoutParams
        val m: Matcher = datePattern.matcher(data.dt_text)
        if (m.find())
            holder.timeTextView.text = "${m.group(3)}-${m.group(2)}-${m.group(1)} ${m.group(4)}:${m.group(5)}:${m.group(6)}"
        else
            holder.timeTextView.text = data.dt_text
        changeMarginByTemp(Math.round(data.temp).toInt())
        holder.tempTextView.text = "${data.temp} °C"
        holder.tempTextView.setBackgroundColor(interpolate(Math.round(data.temp).toInt()))
        holder.windTextView.text = "${data.wind_speed} m/s"
        Picasso.get()
                .load(this.setIcon(data.weather_icon)!!)
                .placeholder(R.drawable.ic_file_download_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(holder.weatherImageView)
        //changeMarginByTemp(dataTest[position])
        //holder.tempTextView.setBackgroundColor(interpolate(dataTest[position]))
        //holder.tempTextView.text = "${dataTest[position]} °C"
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

    private fun interpolateTemp(value: Int): Int {
        val color: Double = value.toDouble()
        return when {
            color <= 0 -> lerpColor(0, 200, 0, 0, 50, 255, Math.abs(color) / 40)
            color in 40..49 -> lerpColor(0, 0, 0, 255, 255, 0, 1.0)
            color >= 50 -> lerpColor(0, 0, 0, 255, 0, 0, 1.0)
            else -> lerpColor(220, 230, 0, 250, 250, 0, color / 50)
        }
    }

    private fun interpolate(value: Int): Int {
        val color: Double = value.toDouble()
        return when {
            color < 0 -> lerpColor(0, 255, 0, 0, 0, 255, Math.abs(color) / 50)
            else -> lerpColor(100, 255, 0, 255, 0, 0, color / 50)
        }
    }

    private fun lerp(start: Double, end: Double, alpha: Double): Double {
        val alphas: Double = when {
            alpha < 0 -> 0.0
            alpha > 1 -> 1.0
            else -> alpha
        }
        return start + (end - start) * alphas
    }

    private fun lerpRound(start: Int, end: Int, alpha: Double): Int {
        Log.d("RGB:","${Math.round(lerp(start.toDouble(), end.toDouble(), alpha)).toInt()}")
        return Math.round(lerp(start.toDouble(), end.toDouble(), alpha)).toInt()
    }

    private fun lerpColor(r1: Int, g1: Int, b1: Int, r2: Int, g2: Int, b2: Int, alpha: Double): Int {
        return Color.rgb(lerpRound(r1, r2, alpha), lerpRound(g1, g2, alpha), lerpRound(b1, b2, alpha))
    }

    private fun changeMarginByTemp(temp: Int) {
        when {
            temp > 0 -> return param.setMargins(0, 100 - temp * 2, 0, 0)
            temp <= 0 -> return param.setMargins(0, 100 + Math.abs(temp) * 2, 0, 0)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weatherImageView: ImageView = view.findViewById(R.id.today_weather_item_weather)
        val timeTextView: TextView = view.findViewById(R.id.today_weather_item_time)
        val tempTextView: TextView = view.findViewById(R.id.today_weather_item_temp)
        val windTextView: TextView = view.findViewById(R.id.today_weather_item_wind)
    }
}