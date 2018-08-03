package com.example.dmitry.weatherchecker.presentation.mainactivity

import com.arellomobile.mvp.MvpView

interface IMainActivity : MvpView {
    fun showMessage(message: String?)
    fun finish()
}