package com.example.dmitry.weatherchecker.presentation.mainactivity

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun showMessage(message: String?)
    fun finish()
}