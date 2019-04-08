package com.example.dmitry.weatherchecker.presentation.configfragment

import android.annotation.SuppressLint
import android.widget.CompoundButton
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.weatherchecker.MainApplication
import com.example.dmitry.weatherchecker.model.CityIdModel
import com.example.dmitry.weatherchecker.other.ScreenKeys
import com.example.dmitry.weatherchecker.other.utils.Utils
import com.example.dmitry.weatherchecker.presentation.configfragment.adapters.ConfigRVAdapter
import com.example.dmitry.weatherchecker.repos.Repos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class ConfigPresenter : MvpPresenter<ConfigView>() {
    private lateinit var repos: Repos
    private lateinit var adapter: ConfigRVAdapter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initView()
        viewState.initView()
    }

    private fun initView() {
        repos = Repos()
        adapter = ConfigRVAdapter(getOnItemListener())
        viewState.setAdapter(adapter)
    }

    private fun getOnItemListener(): ConfigRVAdapter.OnItemClickListener {
        return object : ConfigRVAdapter.OnItemClickListener {
            override fun onItemClick(item: CityIdModel) {
                Utils.saveSettings(item.cityId)
                MainApplication.getRouter().newRootScreen(ScreenKeys.VP_FRAGMENT)
                viewState.showMessage("City set by ${item.name}")
            }
        }
    }

    @SuppressLint("CheckResult")
    fun getCityNames(name: String) {
        repos.getCityIdRx("$name%")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.setData(it as ArrayList<CityIdModel>)
                }
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    fun onClick(buttonView: CompoundButton?, checked: Boolean) {
        if (checked) {
            repos.getNowDataRx()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Utils.saveNotificationStatus(checked)
                        MainApplication.getNotify()?.createNotification(it.reversed())
                    }
        } else {
            MainApplication.getNotify()?.cancelNotification()
            Utils.saveNotificationStatus(checked)
        }
    }
}