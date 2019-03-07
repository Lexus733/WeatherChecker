package com.example.dmitry.weatherchecker.other.Utils

import android.content.Context
import com.example.dmitry.weatherchecker.model.CityId
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStreamReader


class JsonHelper {
    lateinit var inputStreamReader: InputStreamReader

    fun getDataFromJson(context: Context): Array<CityId>? {
        inputStreamReader = InputStreamReader(context.assets.open("citylistmin.json"))
        try {
            return Gson().fromJson(inputStreamReader, Array<CityId>::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return null
    }
}