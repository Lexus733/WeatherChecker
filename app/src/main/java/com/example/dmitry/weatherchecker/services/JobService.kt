package com.example.dmitry.weatherchecker.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import com.example.dmitry.weatherchecker.util.ServiceUtil

class JobService : JobService() {
    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        applicationContext.startService(Intent(applicationContext, ServiceForApi::class.java))
        ServiceUtil().scheduleJob(applicationContext)
        return true
    }

}
