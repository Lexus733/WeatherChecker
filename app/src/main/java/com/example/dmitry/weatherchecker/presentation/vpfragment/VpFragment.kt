package com.example.dmitry.weatherchecker.presentation.vpfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.presentation.viewpagetodayweather.AdapterVPTW
import kotlinx.android.synthetic.main.vp_fragment.*

class VpFragment : MvpAppCompatFragment(), VpFragmentView {
    @InjectPresenter
    lateinit var presenter: VpFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.vp_fragment, container, false)
    }

    override fun setAdapter(it: ArrayList<List<WeatherDataModel>>) {
        view_page.adapter = AdapterVPTW(childFragmentManager, it)
        progressBar.visibility = ProgressBar.GONE
    }


    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}