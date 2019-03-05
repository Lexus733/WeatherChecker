package com.example.dmitry.weatherchecker.presentation.configfragment

import android.view.View
import com.arellomobile.mvp.MvpView

interface ConfigView : MvpView {
    fun initView(click: View.OnClickListener)
}