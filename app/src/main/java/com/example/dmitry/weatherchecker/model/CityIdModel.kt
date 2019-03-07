package com.example.dmitry.weatherchecker.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cityId")
data class CityIdModel(
        @ColumnInfo(name = "cityId") val cityId: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "country") val country: String,
        @ColumnInfo(name = "lon") val lon: Double,
        @ColumnInfo(name = "lat") val lat: Double) {
    @PrimaryKey
    var id: Int? = null
}