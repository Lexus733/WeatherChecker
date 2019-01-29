package com.example.dmitry.weatherchecker.presentation.mainactivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.MainApplication
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.other.ScreenKeys
import com.example.dmitry.weatherchecker.presentation.todayweather.TodayWeatherFragment
import ru.terrakok.cicerone.android.SupportFragmentNavigator

@InjectViewState
class MainActivityPresenter(private val supportFragmentManager: FragmentManager) : MvpPresenter<IMainActivity>() {
    private val navigators: SupportFragmentNavigator
        get() = object : SupportFragmentNavigator(supportFragmentManager, R.id.frame_container) {
            override fun createFragment(screenKey: String?, data: Any?): Fragment {
                return when (screenKey) {
                    ScreenKeys.TODAY_WEATHER -> TodayWeatherFragment()
                    else -> throw RuntimeException()
                }
            }

            override fun exit() {
                viewState.finish()
            }

            override fun showSystemMessage(message: String?) {
                viewState.showMessage("Error!!! Can't access to fragment")
            }
        }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        MainApplication.getRouter().navigateTo(ScreenKeys.TODAY_WEATHER)
    }

    fun onBackPressed() = MainApplication.getRouter().exit()

    fun onResume() = MainApplication.getNavigatorHolder().setNavigator(navigators)

    fun onPause() = MainApplication.getNavigatorHolder().removeNavigator()
}