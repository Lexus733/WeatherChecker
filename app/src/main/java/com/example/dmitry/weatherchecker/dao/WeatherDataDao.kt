package com.example.dmitry.weatherchecker.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.dmitry.weatherchecker.domain.net.ApiQuery
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import io.reactivex.Flowable

@Dao
interface WeatherDataDao {
    @Query(ApiQuery.GET_ALL)
    fun getAll(): List<WeatherDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherData: WeatherDataModel)

    @Query(ApiQuery.GET_ONE_BY_ID)
    fun getOneById(id: Int): List<WeatherDataModel>

    @Query(ApiQuery.GET_LAST_DATA)
    fun getLastData(): List<WeatherDataModel>

    @Query(ApiQuery.GET_10_LAST_DATA)
    fun getLast10Data(): List<WeatherDataModel>

    @Query(ApiQuery.GET_TODAY_DATA)
    fun getTodayData(): List<WeatherDataModel>

    @Query(ApiQuery.GET_NOW_DATA)
    fun getNowData(): List<WeatherDataModel>

    @Query(ApiQuery.GET_FORWARD_DATA)
    fun getForwardData(days: String): List<WeatherDataModel>

    @Query(ApiQuery.GET_NOW_DATA)
    fun getNowDataRX(): Flowable<List<WeatherDataModel>>

    @Query(ApiQuery.GET_TODAY_DATA)
    fun getTodayDataRX(): Flowable<List<WeatherDataModel>>

    @Query(ApiQuery.GET_FORWARD_DATA)
    fun getForwardDataRX(days: String): Flowable<List<WeatherDataModel>>

    @Query(ApiQuery.GET_10_LAST_DATA)
    fun getLast10DataRx(): Flowable<List<WeatherDataModel>>

}