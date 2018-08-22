package com.example.dmitry.weatherchecker.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.dmitry.weatherchecker.model.WeatherDataModel


@Dao
interface WeatherDataDao {
    @Query("SELECT * FROM weatherData")
    fun getAll(): List<WeatherDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherData: WeatherDataModel)

    @Query("DELETE FROM weatherData")
    fun deleteAll()

    @Query("DELETE FROM weatherData WHERE id = :id")
    fun deleteOne(id: Int)

    @Query("SELECT * FROM weatherData WHERE id=:id")
    fun getOne(id: Int): List<WeatherDataModel>

    @Query("SELECT * FROM weatherData order by id DESC LIMIT 1")
    fun getLastData(): List<WeatherDataModel>

    @Query("SELECT * FROM weatherData order by id DESC LIMIT 10")
    fun getLast10Data(): List<WeatherDataModel>

}