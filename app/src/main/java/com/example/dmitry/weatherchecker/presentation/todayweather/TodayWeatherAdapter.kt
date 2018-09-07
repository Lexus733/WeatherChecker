package com.example.dmitry.weatherchecker.presentation.todayweather

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
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

class TodayWeatherAdapter : RecyclerView.Adapter<TodayWeatherAdapter.ViewHolder>() {
    private lateinit var data: ArrayList<WeatherDataModel>
    private lateinit var param: ConstraintLayout.LayoutParams
    private val linearGradient = LinearGradient(0F,0F,200F,0F, intArrayOf(Color.RED,Color.BLUE), floatArrayOf(1F,2F),Shader.TileMode.CLAMP)
    private val dataTest = arrayListOf<Int>(50, 40, 30, 20, 10, 0, -10, -20, -30, -40)
    private val dataTest2 = arrayListOf<Int>(19, 18, 17, 16, 15, 14, 13, 12, 11, 10)
    private val dataTest3 = arrayListOf<Int>(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.today_weather_item_constraint, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: WeatherDataModel = data[position]
        param = holder.tempTextView.layoutParams as ConstraintLayout.LayoutParams
        getGraphMargin(data.temp.toInt())
        //holder.tempTextView.setBackgroundColor(getTemperatureColor(Math.round(data.temp).toInt()))
        //holder.tempTextView.text = "${data.temp} °C"
        //holder.tempTextView.setBackgroundColor(interpolate(Math.round(data.temp).toInt()))
        holder.tempTextView.setBackgroundColor(interpolateNew(dataTest[position]))
        holder.tempTextView.text = "${dataTest[position]} °C"
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

    private fun interpolateNew(value: Int): Int {
        var color: Double = value.toDouble()

        return when {
            color < 10 -> lerpColor(0, 255, 0, 0, 50, 255, Math.abs(color) / 50)
            color >= 40 -> Color.rgb(255,0,0)
            else -> lerpColor(220, 230, 0, 250, 250, 0, color / 50)
        }
    }

    private fun lerp(start: Double, end: Double, alpha: Double): Double {
        val alphas: Double = when {
            alpha < 0 -> 0.0
            alpha > 1 -> 1.0
            else -> alpha
        }
        Log.d("lerp", "${start + (end - start) * alphas}")
        return start + (end - start) * alphas
    }

    private fun lerpRound(start: Int, end: Int, alpha: Double): Int {
        return Math.round(lerp(start.toDouble(), end.toDouble(), alpha)).toInt()
    }

    private fun lerpColor(r1: Int, g1: Int, b1: Int, r2: Int, g2: Int, b2: Int, alpha: Double): Int {
        return Color.rgb(lerpRound(r1, r2, alpha), lerpRound(g1, g2, alpha), lerpRound(b1, b2, alpha))
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