package com.example.dmitry.weatherchecker.other.Utils

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
import com.example.dmitry.weatherchecker.presentation.mainactivity.MainActivity

class NotificationManagerUtils() {
    companion object {
        private const val NOTIFY_ID = 101
        private const val CHANNEL_ID = "WEATHER"

        fun createNotification(context: Context, list: List<WeatherDataModel>) {
            val resultIntent = Intent(context, MainActivity::class.java)
            resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            val resultPendingIntent = PendingIntent.getActivity(
                    context, 0, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            )

            val name = "Weather"
            val descriptionText = "Weather info for current city"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            val remote = RemoteViews(context.packageName, R.layout.notification)
            remote.setTextViewText(R.id.inner_city, "${list[0].city_name}")
            remote.setTextViewText(R.id.inner_temp, "Temp: ${list[0].temp}")
            remote.setTextViewText(R.id.inner_wind, "Wind: ${list[0].wind_speed}")
            remote.setImageViewResource(R.id.weather_icon, Utils.setIcon(list[0].weather_icon)!!)
            remote.setOnClickPendingIntent(R.id.config_icons, resultPendingIntent)
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            builder.setSmallIcon(android.R.drawable.ic_dialog_email)
            builder.priority = NotificationCompat.PRIORITY_DEFAULT
            builder.setStyle(NotificationCompat.DecoratedCustomViewStyle())
            builder.setUsesChronometer(true)
            builder.setOngoing(true)
            builder.setCustomContentView(remote)

            val notificationManagerCompat: NotificationManagerCompat = NotificationManagerCompat.from(context)
            notificationManagerCompat.notify(NOTIFY_ID, builder.build())
        }

        fun cancelNotification(context: Context) {
            val notificationManagerCompat: NotificationManagerCompat = NotificationManagerCompat.from(context)
            notificationManagerCompat.cancel(NOTIFY_ID)
        }
    }
}