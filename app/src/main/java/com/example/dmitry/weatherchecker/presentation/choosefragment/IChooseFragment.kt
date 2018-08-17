package com.example.dmitry.weatherchecker.presentation.choosefragment

import android.content.Intent
import android.view.View
import com.arellomobile.mvp.MvpView

interface IChooseFragment : MvpView {
    fun initView(onClickListenerGoToTodayWeatherFragment: View.OnClickListener, onClickListenerGoToWeatherGraphs: View.OnClickListener, onClickListenerStopService: View.OnClickListener)
    fun stopService(intent: Intent?)
}