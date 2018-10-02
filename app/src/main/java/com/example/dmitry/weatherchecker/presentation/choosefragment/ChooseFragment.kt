package com.example.dmitry.weatherchecker.presentation.choosefragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import kotlinx.android.synthetic.main.choose_fragment.*


class ChooseFragment : MvpAppCompatFragment(), IChooseFragment {
    private lateinit var powerManager: PowerManager
    @InjectPresenter
    lateinit var presenter: ChooseFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.choose_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun initView(onClickListenerGoToTodayWeatherFragment: View.OnClickListener, onClickListenerGoToWeatherGraphs: View.OnClickListener, onClickListenerStopService: View.OnClickListener) {
        btn_to_today_weather_fragment.setOnClickListener(onClickListenerGoToTodayWeatherFragment)
        btn_to_graphs_weather.setOnClickListener(onClickListenerGoToWeatherGraphs)
        btn_stop_service.setOnClickListener(onClickListenerStopService)
    }

    override fun stopService(intent: Intent?) {
        context!!.stopService(intent)
    }
}