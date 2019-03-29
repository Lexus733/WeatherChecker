package com.example.dmitry.weatherchecker.presentation.mainactivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.MainApplication
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.other.ScreenKeys
import com.example.dmitry.weatherchecker.other.utils.Utils
import com.example.dmitry.weatherchecker.other.WeatherApiKeys
import com.example.dmitry.weatherchecker.presentation.configfragment.ConfigFragment
import com.example.dmitry.weatherchecker.presentation.vpfragment.VpFragment
import ru.terrakok.cicerone.android.SupportFragmentNavigator

@InjectViewState
class MainActivityPresenter(private val supportFragmentManager: FragmentManager) : MvpPresenter<MainView>() {
    private val navigators: SupportFragmentNavigator
        get() = object : SupportFragmentNavigator(supportFragmentManager, R.id.frame_layout) {
            override fun createFragment(screenKey: String?, data: Any?): Fragment {
                return when (screenKey) {
                    ScreenKeys.VP_FRAGMENT -> VpFragment()
                    ScreenKeys.CONFIG_FRAGMENT -> ConfigFragment()
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
        if (Utils.loadSettings() != WeatherApiKeys.FIRST_START)
            MainApplication.getRouter().navigateTo(ScreenKeys.VP_FRAGMENT)
        else
            MainApplication.getRouter().navigateTo(ScreenKeys.CONFIG_FRAGMENT)
    }

    fun onBackPressed() = MainApplication.getRouter().exit()

    fun onResume() = MainApplication.getNavigatorHolder().setNavigator(navigators)

    fun onPause() = MainApplication.getNavigatorHolder().removeNavigator()
}