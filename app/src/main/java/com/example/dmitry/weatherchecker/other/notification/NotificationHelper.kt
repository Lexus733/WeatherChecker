package com.example.dmitry.weatherchecker.other.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.widget.RemoteViews
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.model.WeatherDataModel
import com.example.dmitry.weatherchecker.other.utils.Utils
import com.example.dmitry.weatherchecker.presentation.mainactivity.MainActivity

class NotificationHelper {
    companion object {
        private var INSTANCE: NotificationHelper? = null
        private var notificationManager: NotificationManager? = null
        private var notificationManagerCompat: NotificationManagerCompat? = null
        private var resultIntent: Intent? = null
        private var resultPendingIntent: PendingIntent? = null
        private var remote: RemoteViews? = null
        private var builder: NotificationCompat.Builder? = null

        fun getInstance(context: Context): NotificationHelper? {
            if (INSTANCE == null) {
                synchronized(NotificationHelper::class) {
                    INSTANCE = NotificationHelper()
                    notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManagerCompat = NotificationManagerCompat.from(context)
                    resultIntent = Intent(context, MainActivity::class.java)
                    resultPendingIntent = PendingIntent.getActivity(
                            context, 0, resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    )
                    remote = RemoteViews(context.packageName, R.layout.notification)
                    builder = NotificationCompat.Builder(context, CHANNEL_ID)
                }
            }
            return INSTANCE
        }

        private const val NOTIFY_ID = 101
        private const val CHANNEL_ID = "WEATHER"
        private const val NAME = "Weather"
        private const val DESCRIPTION_TEXT = "Weather info for current city"
    }

    fun createNotification(list: List<WeatherDataModel>) {
        resultIntent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, NAME, importance).apply {
            description = DESCRIPTION_TEXT
        }

        notificationManager?.createNotificationChannel(channel)

        remote?.setTextViewText(R.id.inner_city, list[0].city_name)
        remote?.setTextViewText(R.id.inner_temp, "Temp: ${list[0].temp}")
        remote?.setTextViewText(R.id.inner_wind, "Wind: ${list[0].wind_speed}")
        remote?.setImageViewResource(R.id.weather_icon, Utils.setIcon(list[0].weather_icon)!!)
        remote?.setOnClickPendingIntent(R.id.config_icons, resultPendingIntent)

        builder?.setSmallIcon(Utils.setIcon(list[0].weather_icon)!!)
        builder?.priority = NotificationCompat.PRIORITY_DEFAULT
        builder?.setStyle(NotificationCompat.DecoratedCustomViewStyle())
        builder?.setUsesChronometer(true)
        builder?.setOngoing(true)
        builder?.setCustomContentView(remote)

        notificationManagerCompat?.notify(NOTIFY_ID, builder?.build()!!)
    }

    fun cancelNotification() {
        notificationManagerCompat?.cancel(NOTIFY_ID)
    }
}