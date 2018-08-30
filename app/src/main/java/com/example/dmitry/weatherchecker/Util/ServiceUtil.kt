package com.example.dmitry.weatherchecker.Util

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context

class ServiceUtil {
    fun scheduleJob(context: Context){
        val serviceComponentName = ComponentName(context,com.example.dmitry.weatherchecker.services.JobService::class.java)
        val builder = JobInfo.Builder(0,serviceComponentName)
        builder.setPeriodic(3600000)

        val jobScheduler = context.getSystemService(JobScheduler::class.java)
        jobScheduler.schedule(builder.build())
    }

}