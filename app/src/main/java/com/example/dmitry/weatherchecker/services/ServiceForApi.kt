package com.example.dmitry.weatherchecker.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.dmitry.weatherchecker.other.utils.NotificationManagerUtils
import com.example.dmitry.weatherchecker.other.utils.Utils
import com.example.dmitry.weatherchecker.repos.Repos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ServiceForApi : Service() {
    companion object {
        private var refreshData: (() -> Unit)? = null
    }
    private lateinit var repos: Repos
    override fun onCreate() {
        super.onCreate()
        repos = Repos()
        repos.insertEverythingToDbFromApi()
        if (Utils.loadNotificationStatus()) {
            refreshData = {
                repos.getNowDataRx()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            NotificationManagerUtils.createNotification(applicationContext, it.reversed())
                        }
            }
        }
        stopSelf()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ServiceLauncher.listener?.invoke()
        repos.insertEverythingToDbFromApi()
        if (Utils.loadNotificationStatus()) {
            refreshData = {
                repos.getNowDataRx()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            NotificationManagerUtils.createNotification(applicationContext, it.reversed())
                        }
            }
        }
        stopSelf()
        return START_STICKY
    }
}
