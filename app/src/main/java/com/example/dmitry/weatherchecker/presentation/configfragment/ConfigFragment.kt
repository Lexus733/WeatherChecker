package com.example.dmitry.weatherchecker.presentation.configfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R

class ConfigFragment : MvpAppCompatFragment(), ConfigView {
    @InjectPresenter
    lateinit var presenter : ConfigPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_confing, container, false)
    }
}
