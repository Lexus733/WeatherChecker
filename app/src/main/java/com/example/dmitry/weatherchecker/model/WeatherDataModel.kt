package com.example.dmitry.weatherchecker.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weatherData")
data class WeatherDataModel(@ColumnInfo(name = "dt") val dt: Int,
                            @ColumnInfo(name = "temp") val temp: Double,
                            @ColumnInfo(name = "temp_min") val temp_min: Double,
                            @ColumnInfo(name = "temp_max") val temp_max: Double,
                            @ColumnInfo(name = "pressure") val pressure: Double,
                            @ColumnInfo(name = "sea_level") val sea_level: Double,
                            @ColumnInfo(name = "grnd_level") val grnd_level: Double,
                            @ColumnInfo(name = "humidity") val humidity: Int,
                            @ColumnInfo(name = "temp_kf") val temp_kf: Double,
                            @ColumnInfo(name = "weather_main") val weather_main: String,
                            @ColumnInfo(name = "weather_description") val weather_description: String,
                            @ColumnInfo(name = "weather_icon") val weather_icon: String,
                            @ColumnInfo(name = "clouds_all") val clouds_all: Int,
                            @ColumnInfo(name = "wind_speed") val wind_speed: Double,
                            @ColumnInfo(name = "wind_deg") val wind_deg: Double,
                            @ColumnInfo(name = "dt_text") val dt_text: String,
                            @ColumnInfo(name = "city_name") val city_name: String,
                            @ColumnInfo(name = "city_country") val city_contry: String) {
    @PrimaryKey(autoGenerate = true) var id: Int? = null
}