package com.example.dmitry.weatherchecker.presentation.configfragment

import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class ConfigPresenter : MvpPresenter<ConfigView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView(onClick())
    }

    private fun onClick(): View.OnClickListener {
        return View.OnClickListener {
        }
    }
}