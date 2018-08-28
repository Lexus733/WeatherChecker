package com.example.dmitry.weatherchecker.presentation.weathergraphs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import kotlinx.android.synthetic.main.weather_graphs_fragment.*

class WeatherGraphsFragment : MvpAppCompatFragment(), IWeatherGraphs {
    @InjectPresenter
    lateinit var presenter: WeatherGraphsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.subs()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsub()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.weather_graphs_fragment, container, false)
    }

    override fun initView(floatArray: FloatArray) {
        graph_view.setData(floatArray)
    }


}