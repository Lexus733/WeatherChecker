package com.example.dmitry.weatherchecker.presentation.configfragment

import com.arellomobile.mvp.MvpView
import com.example.dmitry.weatherchecker.presentation.configfragment.adapters.ConfigRVAdapter

interface ConfigView : MvpView {
    fun initView()
    fun setAdapter(adapter: ConfigRVAdapter)
    fun showMessage(message: String)
}