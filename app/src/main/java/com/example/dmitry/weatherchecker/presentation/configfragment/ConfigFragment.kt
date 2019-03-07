package com.example.dmitry.weatherchecker.presentation.configfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import kotlinx.android.synthetic.main.fragment_confing.*

class ConfigFragment : MvpAppCompatFragment(), ConfigView {
    @InjectPresenter
    lateinit var presenter: ConfigPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_confing, container, false)
    }

    override fun initView(click: View.OnClickListener) {
        config_apply_button.setOnClickListener(click)
//        Thread(Runnable {
//            JsonHelper().getDataFromJson(this!!.context!!)!!.map {
//                var model = CityIdModel(it.id,it.name,it.country,it.coord.lon,it.coord.lat)
//                repos.insertCityIdDataInDb(model)
//                count++
//                Log.d("Count","Count:${count}")
//            }
//        }).start()

    }


}
