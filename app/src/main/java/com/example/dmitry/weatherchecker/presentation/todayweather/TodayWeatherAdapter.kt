package com.example.dmitry.weatherchecker.presentation.todayweather

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.other.Utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.today_weather_item_constraint.view.*
import org.jetbrains.anko.withAlpha

class TodayWeatherAdapter : RecyclerView.Adapter<TodayWeatherAdapter.ViewHolder>() {
    private var data: ArrayList<WeatherDataModel> = ArrayList()
    private lateinit var param: ConstraintLayout.LayoutParams

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.today_weather_item_constraint, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: WeatherDataModel = data[position]
        holder.timeTextView.text = Utils.dateTimeEdit(data.dt_text)
        param = holder.tempTextView.layoutParams as ConstraintLayout.LayoutParams
        holder.tempTextView.text = "${data.temp} °C"
        holder.tempTextView.setBackgroundColor(Utils.colorByTemp(Math.round(data.temp).toInt()))
        holder.constraintLayout.setBackgroundColor(Utils.colorByTemp(Math.round(data.temp).toInt())
                .withAlpha(25))
        holder.windTextView.text = "${data.wind_speed} м/с"
        Picasso.get()
                .load(Utils.setIcon(data.weather_icon)!!)
                .placeholder(R.drawable.ic_file_download_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(holder.weatherImageView)
    }

    fun setData(data: ArrayList<WeatherDataModel>) {
        if (data.size > 0) {
            this.data = data
            notifyDataSetChanged()
        }
    }

    private fun changeMarginByTemp(temp: Int) {
        when {
            temp > 0 -> return param.setMargins(0, 100 - temp * 2, 0, 0)
            temp <= 0 -> return param.setMargins(0, 100 + Math.abs(temp) * 2, 0, 0)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weatherImageView: ImageView = view.today_weather_item_weather
        val timeTextView: TextView = view.today_weather_item_time
        val tempTextView: TextView = view.today_weather_item_temp
        val windTextView: TextView = view.today_weather_item_wind
        val constraintLayout: ConstraintLayout = view.constraint_item
    }
}