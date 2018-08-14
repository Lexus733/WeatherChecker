package com.example.dmitry.weatherchecker.presentation.weathergraphs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R

class WeatherGraphsFragment : MvpAppCompatFragment(), IWeatherGraphs {
    @InjectPresenter
    lateinit var presenter: WeatherGraphsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.weather_graphs_fragment, container, false)
    }
}