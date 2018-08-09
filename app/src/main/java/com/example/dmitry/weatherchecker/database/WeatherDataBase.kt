package com.example.dmitry.weatherchecker.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.dmitry.weatherchecker.dao.WeatherDataDao
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.other.DataBaseKeys

@Database(entities = [WeatherDataModel::class], version = 1)
abstract class WeatherDataBase : RoomDatabase() {
    abstract fun weatherDataDao(): WeatherDataDao

    companion object {
        private var INSTANCE: WeatherDataBase? = null

        fun getInstance(context: Context): WeatherDataBase? {
            if (INSTANCE == null) {
                synchronized(WeatherDataBase::class) {
                    INSTANCE = Room
                            .databaseBuilder(context.applicationContext, WeatherDataBase::class.java
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