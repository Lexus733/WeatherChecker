package com.example.dmitry.weatherchecker.presentation.viewpagetodayweather

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.dmitry.weatherchecker.model.WeatherDataModel

class AdapterVPTW(mgr: FragmentManager, private val arg: ArrayList<List<WeatherDataModel>>) : FragmentStatePagerAdapter(mgr) {
    override fun getItem(p0: Int): Fragment {
        return TodayWeatherFragmentVP.newInstance(arg[p0])
    }

    override fun getCount(): Int {
        return arg.size
    }

}