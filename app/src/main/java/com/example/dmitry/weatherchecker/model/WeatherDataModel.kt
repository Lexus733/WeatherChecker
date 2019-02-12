package com.example.dmitry.weatherchecker.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

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
                            @ColumnInfo(name = "city_country") val city_contry: String) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dt)
        parcel.writeDouble(temp)
        parcel.writeDouble(temp_min)
        parcel.writeDouble(temp_max)
        parcel.writeDouble(pressure)
        parcel.writeDouble(sea_level)
        parcel.writeDouble(grnd_level)
        parcel.writeInt(humidity)
        parcel.writeDouble(temp_kf)
        parcel.writeString(weather_main)
        parcel.writeString(weather_description)
        parcel.writeString(weather_icon)
        parcel.writeInt(clouds_all)
        parcel.writeDouble(wind_speed)
        parcel.writeDouble(wind_deg)
        parcel.writeString(dt_text)
        parcel.writeString(city_name)
        parcel.writeString(city_contry)
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherDataModel> {
        override fun createFromParcel(parcel: Parcel): WeatherDataModel {
            return WeatherDataModel(parcel)
        }

        override fun newArray(size: Int): Array<WeatherDataModel?> {
            return arrayOfNulls(size)
        }
    }
}