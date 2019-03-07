package com.example.dmitry.weatherchecker.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.dmitry.weatherchecker.dao.WeatherDataDao
import com.example.dmitry.weatherchecker.model.CityIdModel
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.other.DataBaseKeys
import com.example.dmitry.weatherchecker.other.Utils.Utils


@Database(entities = [WeatherDataModel::class, CityIdModel::class], version = 3)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun weatherDataDao(): WeatherDataDao

    companion object {
        private var INSTANCE: WeatherDataBase? = null

        fun getInstance(context: Context): WeatherDataBase? {
            if (INSTANCE == null) {
                synchronized(WeatherDataBase::class) {
                    Utils.copyAttachedDatabase(context,DataBaseKeys.WEATHER_DB)
                    INSTANCE = Room
                            .databaseBuilder(context.applicationContext
                                    , WeatherDataBase::class.java
                                    , DataBaseKeys.WEATHER_DB)
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}