package com.example.dmitry.weatherchecker.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.other.ApiQuery


@Dao
interface WeatherDataDao {
    @Query(ApiQuery.GET_ALL)
    fun getAll(): List<WeatherDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherData: WeatherDataModel)

    @Query(ApiQuery.DELETE_ALL)
    fun deleteAll()

    @Query(ApiQuery.DELETE_ONE)
    fun deleteOne(id: Int)

    @Query(ApiQuery.GET_ONE_BY_ID)
    fun getOneById(id: Int): List<WeatherDataModel>

    @Query(ApiQuery.GET_LAST_DATA)
    fun getLastData(): List<WeatherDataModel>

    @Query(ApiQuery.GET_10_LAST_DATA)
    fun getLast10Data(): List<WeatherDataModel>

}