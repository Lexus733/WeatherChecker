package com.example.dmitry.weatherchecker.presentation.choosefragment

import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.MainApplication
import com.example.dmitry.weatherchecker.other.ScreenKeys

@InjectViewState
class ChooseFragmentPresenter : MvpPresenter<IChooseFragment>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView(onClickListenerGoToTodayWeatherFragment
                , onClickListenerGoToWeatherGraphs,onClickListenerStopService)
    }

    private var onClickListenerGoToTodayWeatherFragment: View.OnClickListener = View.OnClickListener {
        MainApplication.getRouter().navigateTo(ScreenKeys.TODAY_WEATHER)
    }

    private var onClickListenerGoToWeatherGraphs: View.OnClickListener = View.OnClickListener {
        MainApplication.getRouter().navigateTo(ScreenKeys.WEATHER_GRAPHS)
    }

    private var onClickListenerStopService: View.OnClickListener = View.OnClickListener {
        viewState.stopService(MainApplication.getIntent())
    }
}