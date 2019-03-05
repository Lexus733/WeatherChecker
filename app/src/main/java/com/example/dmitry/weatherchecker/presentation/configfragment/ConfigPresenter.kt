package com.example.dmitry.weatherchecker.presentation.configfragment

import android.content.SharedPreferences
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.MainApplication
import com.example.dmitry.weatherchecker.other.SharedPreferencesKeys
import com.example.dmitry.weatherchecker.other.Utils.Utils

@InjectViewState
class ConfigPresenter : MvpPresenter<ConfigView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView(onClick())
    }

    private fun onClick(): View.OnClickListener {
        return View.OnClickListener{
            Utils.saveSettings(707860)
        }
    }
}