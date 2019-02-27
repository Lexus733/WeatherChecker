package com.example.dmitry.weatherchecker.other.Utils

import android.graphics.Color
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.other.RegexKeys
import java.util.regex.Matcher
import java.util.regex.Pattern

class Utils {
    companion object {
        fun dateTimeEdit(dt_text: String): String {
            val m: Matcher = Pattern.compile(RegexKeys.DATE_PATTERN).matcher(dt_text)
            if (m.find())
                return "${m.group(3)}-${m.group(2)}-${m.group(1)} " +
                        "${m.group(4)}:${m.group(5)}:${m.group(6)}"
            else
                return dt_text
        }

        fun dateEdit(dt_text: String): String {
            val m: Matcher = Pattern.compile(RegexKeys.DATE_PATTERN).matcher(dt_text)
            if (m.find())
                return "${m.group(3)}-${m.group(2)}-${m.group(1)} "
            else
                return dt_text
        }

        fun setIcon(id: String): Int? {
            when (id) {
                "01d" -> return R.drawable.sun
                "01n" -> return R.drawable.moon
                "02d" -> return R.drawable.cloudsun
                "02n" -> return R.drawable.cloudnight
                "03d" -> return R.drawable.clouds
                "03n" -> return R.drawable.cloudsnight
                "04d" -> return R.drawable.twoclouds
                "04n" -> return R.drawable.twocloudsnight
                "09d" -> return R.drawable.rain
                "09n" -> return R.drawable.rainnight
                "10d" -> return R.drawable.cloudrainsun
                "10n" -> return R.drawable.cloudrainsunnight
                "11d" -> return R.drawable.lightning
                "11n" -> return R.drawable.lightningnight
                "13d" -> return R.drawable.snow
                "13n" -> return R.drawable.snownight
                "50d" -> return R.drawable.dry
                "50n" -> return R.drawable.dry
            }
            return R.drawable.ic_error_black_24dp
        }

        fun colorByTemp(temp: Int): Int {
            if (temp > 0) {
                return when (temp) {
                    50 -> Color.rgb(240, 0, 0)
                    49 -> Color.rgb(240, 25, 0)
                    48 -> Color.rgb(240, 50, 0)
                    47 -> Color.rgb(240, 75, 0)
                    46 -> Color.rgb(240, 100, 0)
                    45 -> Color.rgb(240, 125, 0)
                    44 -> Color.rgb(240, 150, 0)
                    43 -> Color.rgb(240, 175, 0)
                    42 -> Color.rgb(240, 200, 0)
                    41 -> Color.rgb(240, 225, 0)
                    40 -> Color.rgb(240, 250, 0)
                    39 -> Color.rgb(228, 240, 0)
                    38 -> Color.rgb(216, 240, 0)
                    37 -> Color.rgb(204, 240, 0)
                    36 -> Color.rgb(192, 240, 0)
                    35 -> Color.rgb(180, 240, 0)
                    34 -> Color.rgb(168, 240, 0)
                    33 -> Color.rgb(156, 240, 0)
                    32 -> Color.rgb(144, 240, 0)
                    31 -> Color.rgb(132, 240, 0)
                    30 -> Color.rgb(120, 240, 0)
                    29 -> Color.rgb(108, 240, 0)
                    28 -> Color.rgb(96, 240, 0)
                    27 -> Color.rgb(84, 240, 0)
                    26 -> Color.rgb(72, 240, 0)
                    25 -> Color.rgb(60, 240, 0)
                    24 -> Color.rgb(48, 240, 0)
                    23 -> Color.rgb(36, 240, 0)
                    22 -> Color.rgb(24, 240, 0)
                    21 -> Color.rgb(12, 240, 0)
                    20 -> Color.rgb(0, 240, 0)
                    19 -> Color.rgb(0, 240, 12)
                    18 -> Color.rgb(0, 240, 24)
                    17 -> Color.rgb(0, 240, 36)
                    16 -> Color.rgb(0, 240, 48)
                    15 -> Color.rgb(0, 240, 60)
                    14 -> Color.rgb(0, 240, 72)
                    13 -> Color.rgb(0, 240, 84)
                    12 -> Color.rgb(0, 240, 96)
                    11 -> Color.rgb(0, 240, 108)
                    10 -> Color.rgb(0, 240, 120)
                    9 -> Color.rgb(0, 240, 132)
                    8 -> Color.rgb(0, 240, 144)
                    7 -> Color.rgb(0, 240, 156)
                    6 -> Color.rgb(0, 240, 168)
                    5 -> Color.rgb(0, 240, 180)
                    4 -> Color.rgb(0, 240, 192)
                    3 -> Color.rgb(0, 240, 204)
                    2 -> Color.rgb(0, 240, 216)
                    1 -> Color.rgb(0, 240, 228)
                    0 -> Color.rgb(0, 240, 240)
                    else -> {
                        Color.rgb(255, 0, 0)
                    }
                }
            } else {
                return when (temp) {
                    in -0 downTo -5 -> Color.rgb(0, 215, 240)
                    in -6 downTo -10 -> Color.rgb(0, 185, 240)
                    in -11 downTo -15 -> Color.rgb(0, 160, 240)
                    in -16 downTo -20 -> Color.rgb(0, 155, 240)
                    in -21 downTo -25 -> Color.rgb(0, 130, 240)
                    in -26 downTo -30 -> Color.rgb(0, 105, 240)
                    in -31 downTo -35 -> Color.rgb(0, 80, 240)
                    in -36 downTo -40 -> Color.rgb(0, 55, 240)
                    in -41 downTo -45 -> Color.rgb(0, 30, 240)
                    else -> {
                        Color.rgb(0, 0, 255)
                    }
                }
            }
        }
    }
}