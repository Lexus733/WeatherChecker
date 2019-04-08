package com.example.dmitry.weatherchecker.presentation.configfragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.other.utils.Utils
import com.example.dmitry.weatherchecker.presentation.configfragment.adapters.ConfigRVAdapter
import kotlinx.android.synthetic.main.fragment_confing.*

class ConfigFragment : MvpAppCompatFragment(), ConfigView {
    @InjectPresenter
    lateinit var presenter: ConfigPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_confing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        config_item_checkNotification.isChecked = Utils.loadNotificationStatus()
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun setAdapter(adapter: ConfigRVAdapter) {
        config_list.adapter = adapter
    }


    override fun initView() {
        configEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.getCityNames(p0.toString())
            }
        })

        config_item_checkNotification.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.onClick(buttonView, isChecked)
        }

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
