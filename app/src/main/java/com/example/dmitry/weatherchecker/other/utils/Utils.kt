package com.example.dmitry.weatherchecker.other.utils

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.dmitry.weatherchecker.MainApplication
import com.example.dmitry.weatherchecker.R
import com.example.dmitry.weatherchecker.other.RegexKeys
import com.example.dmitry.weatherchecker.other.SharedPreferencesKeys
import com.example.dmitry.weatherchecker.other.WeatherApiKeys
import java.io.FileOutputStream
import java.io.IOException
import java.util.regex.Matcher
import java.util.regex.Pattern


class Utils {
    companion object {
        fun darkenColor(color: Int): Int {
            val hsv = FloatArray(3)
            Color.colorToHSV(color, hsv)
            hsv[2] *= 0.8f
            return Color.HSVToColor(hsv)
        }

        fun darkenStatusBar(window: Window?, color: Int) {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = color
        }

        fun copyAttachedDatabase(context: Context, databaseName: String) {
            val dbPath = context.getDatabasePath(databaseName)

            if (dbPath.exists()) {
                return
            }

            dbPath.parentFile.mkdirs()

            try {
                val inputStream = context.assets.open(databaseName)
                val output = FileOutputStream(dbPath)

                val buffer = ByteArray(8192)
                var length: Int

                while (inputStream.read(buffer, 0, 8192).let {
                            length = it
                            it > 0
                        }) {
                    output.write(buffer, 0, length)
                }

                output.flush()
                output.close()
                inputStream.close()
            } catch (e: IOException) {
                Log.d("TAG", "Failed to open file", e)
                e.printStackTrace()
            }

        }

        fun loadSettings(): Int {
            return MainApplication.getSP()
                    .getInt(SharedPreferencesKeys.CITY_ID, WeatherApiKeys.FIRST_START)
        }

        fun loadNotificationStatus(): Boolean {
            return MainApplication.getSP()
                    .getBoolean(SharedPreferencesKeys.NOTIFICATION_STATUS, false)
        }

        fun saveNotificationStatus(check: Boolean) {
            val editor: SharedPreferences.Editor = MainApplication.getSP().edit()
            editor.putBoolean(SharedPreferencesKeys.NOTIFICATION_STATUS, check)
            editor.apply()
        }

        fun saveSettings(id: Int) {
            val editor: SharedPreferences.Editor = MainApplication.getSP().edit()
            editor.putInt(SharedPreferencesKeys.CITY_ID, id)
            editor.apply()
        }

        fun dateTimeEdit(dt_text: String): String {
            val m: Matcher = Pattern.compile(RegexKeys.DATE_PATTERN).matcher(dt_text)
            return if (m.find())
                "${m.group(3)}-${m.group(2)}-${m.group(1)} " +
                        "${m.group(4)}:${m.group(5)}:${m.group(6)}"
            else
                dt_text
        }

        fun dateEdit(dt_text: String): String {
            val m: Matcher = Pattern.compile(RegexKeys.DATE_PATTERN).matcher(dt_text)
            return if (m.find())
                "${m.group(3)}-${m.group(2)}-${m.group(1)} "
            else
                dt_text
        }

        fun setCountryIcon(country: String): Int {
            when (country) {
                "AD" -> return R.drawable.ad
                "AE" -> return R.drawable.ae
                "AG" -> return R.drawable.ag
                "AI" -> return R.drawable.ai
                "AL" -> return R.drawable.al
                "AM" -> return R.drawable.am
                "AO" -> return R.drawable.ao
                "AR" -> return R.drawable.ar
                "AS" -> return R.drawable.`as`
                "AT" -> return R.drawable.at
                "AU" -> return R.drawable.au
                "AW" -> return R.drawable.aw
                "AX" -> return R.drawable.ax
                "AZ" -> return R.drawable.az
                "BA" -> return R.drawable.ba
                "BB" -> return R.drawable.bb
                "BD" -> return R.drawable.bd
                "BE" -> return R.drawable.be
                "BF" -> return R.drawable.bf
                "BG" -> return R.drawable.bg
                "BH" -> return R.drawable.bh
                "BI" -> return R.drawable.bi
                "BJ" -> return R.drawable.bj
                "BM" -> return R.drawable.bm
                "BN" -> return R.drawable.bn
                "BO" -> return R.drawable.bo
                "BQ" -> return R.drawable.bq
                "BR" -> return R.drawable.br
                "BS" -> return R.drawable.bs
                "BT" -> return R.drawable.bt
                "BW" -> return R.drawable.bw
                "BY" -> return R.drawable.by
                "BZ" -> return R.drawable.bz
                "CA" -> return R.drawable.ca
                "CC" -> return R.drawable.cc
                "CD" -> return R.drawable.cd
                "CF" -> return R.drawable.cf
                "CG" -> return R.drawable.cg
                "CH" -> return R.drawable.ch
                "CI" -> return R.drawable.ci
                "CK" -> return R.drawable.ck
                "CL" -> return R.drawable.cl
                "CM" -> return R.drawable.cm
                "CN" -> return R.drawable.cn
                "CO" -> return R.drawable.co
                "CR" -> return R.drawable.cr
                "CU" -> return R.drawable.cu
                "CV" -> return R.drawable.cv
                "CW" -> return R.drawable.cw
                "CX" -> return R.drawable.cx
                "CY" -> return R.drawable.cy
                "CZ" -> return R.drawable.cz
                "DE" -> return R.drawable.de
                "DJ" -> return R.drawable.dj
                "DK" -> return R.drawable.dk
                "DM" -> return R.drawable.dm
                "DO" -> return R.drawable.doo
                "DZ" -> return R.drawable.dz
                "EC" -> return R.drawable.ec
                "EE" -> return R.drawable.ee
                "EG" -> return R.drawable.eg
                "ER" -> return R.drawable.er
                "ES" -> return R.drawable.es
                "ET" -> return R.drawable.et
                "FJ" -> return R.drawable.fj
                "FK" -> return R.drawable.fk
                "FL" -> return R.drawable.fl
                "FM" -> return R.drawable.fm
                "FO" -> return R.drawable.fo
                "FR" -> return R.drawable.fr
                "GA" -> return R.drawable.ga
                "GB" -> return R.drawable.gb
                "GD" -> return R.drawable.gd
                "GE" -> return R.drawable.ge
                "GG" -> return R.drawable.gg
                "GH" -> return R.drawable.gh
                "GI" -> return R.drawable.gi
                "GL" -> return R.drawable.gl
                "GM" -> return R.drawable.gm
                "GN" -> return R.drawable.gn
                "GQ" -> return R.drawable.gq
                "GR" -> return R.drawable.gr
                "GT" -> return R.drawable.gt
                "GU" -> return R.drawable.gu
                "HI" -> return R.drawable.hi
                "HK" -> return R.drawable.hk
                "HR" -> return R.drawable.hr
                "HT" -> return R.drawable.ht
                "HU" -> return R.drawable.hu
                "ID" -> return R.drawable.id
                "IE" -> return R.drawable.ie
                "IL" -> return R.drawable.il
                "IM" -> return R.drawable.im
                "IN" -> return R.drawable.`in`
                "IO" -> return R.drawable.io
                "IR" -> return R.drawable.ir
                "IS" -> return R.drawable.`is`
                "IT" -> return R.drawable.it
                "JE" -> return R.drawable.je
                "JM" -> return R.drawable.jm
                "JO" -> return R.drawable.jo
                "JP" -> return R.drawable.jp
                "KE" -> return R.drawable.ke
                "KG" -> return R.drawable.kg
                "KH" -> return R.drawable.kh
                "KI" -> return R.drawable.ki
                "KM" -> return R.drawable.km
                "KN" -> return R.drawable.kn
                "KR" -> return R.drawable.kr
                "KW" -> return R.drawable.kw
                "KY" -> return R.drawable.ky
                "KZ" -> return R.drawable.kz
                "LA" -> return R.drawable.la
                "LB" -> return R.drawable.lb
                "LC" -> return R.drawable.lc
                "LI" -> return R.drawable.li
                "LK" -> return R.drawable.lk
                "LR" -> return R.drawable.lr
                "LS" -> return R.drawable.ls
                "LT" -> return R.drawable.lt
                "LU" -> return R.drawable.lu
                "LV" -> return R.drawable.lv
                "LY" -> return R.drawable.ly
                "MA" -> return R.drawable.ma
                "MC" -> return R.drawable.mc
                "MD" -> return R.drawable.md
                "ME" -> return R.drawable.me
                "MG" -> return R.drawable.mg
                "MH" -> return R.drawable.mh
                "MK" -> return R.drawable.mk
                "ML" -> return R.drawable.ml
                "MM" -> return R.drawable.mm
                "MN" -> return R.drawable.mn
                "MO" -> return R.drawable.mo
                "MP" -> return R.drawable.mp
                "MQ" -> return R.drawable.mq
                "MR" -> return R.drawable.mr
                "MS" -> return R.drawable.ms
                "MT" -> return R.drawable.mt
                "MU" -> return R.drawable.mu
                "MV" -> return R.drawable.mv
                "MW" -> return R.drawable.mw
                "MX" -> return R.drawable.mx
                "MY" -> return R.drawable.my
                "MZ" -> return R.drawable.mz
                "NA" -> return R.drawable.na
                "NE" -> return R.drawable.ne
                "NF" -> return R.drawable.nf
                "NG" -> return R.drawable.ng
                "NH" -> return R.drawable.nh
                "NI" -> return R.drawable.ni
                "NL" -> return R.drawable.nl
                "NO" -> return R.drawable.no
                "NP" -> return R.drawable.np
                "NR" -> return R.drawable.nr
                "NU" -> return R.drawable.nu
                "NZ" -> return R.drawable.nz
                "OM" -> return R.drawable.om
                "PA" -> return R.drawable.pa
                "PE" -> return R.drawable.pe
                "PF" -> return R.drawable.pf
                "PG" -> return R.drawable.pg
                "PH" -> return R.drawable.ph
                "PK" -> return R.drawable.pk
                "PL" -> return R.drawable.pl
                "PN" -> return R.drawable.pn
                "PR" -> return R.drawable.pr
                "PS" -> return R.drawable.ps
                "PT" -> return R.drawable.pt
                "PW" -> return R.drawable.pw
                "PY" -> return R.drawable.py
                "RO" -> return R.drawable.ro
                "RS" -> return R.drawable.rs
                "RU" -> return R.drawable.ru
                "RW" -> return R.drawable.rw
                "SB" -> return R.drawable.sb
                "SC" -> return R.drawable.sc
                "SD" -> return R.drawable.sd
                "SE" -> return R.drawable.se
                "SG" -> return R.drawable.sg
                "SI" -> return R.drawable.si
                "SK" -> return R.drawable.sk
                "SL" -> return R.drawable.sl
                "SM" -> return R.drawable.sm
                "SN" -> return R.drawable.sn
                "SO" -> return R.drawable.so
                "SR" -> return R.drawable.sr
                "SS" -> return R.drawable.ss
                "ST" -> return R.drawable.st
                "SV" -> return R.drawable.sv
                "SX" -> return R.drawable.sx
                "SY" -> return R.drawable.sy
                "SZ" -> return R.drawable.sz
                "TC" -> return R.drawable.tc
                "TD" -> return R.drawable.td
                "TG" -> return R.drawable.tg
                "TH" -> return R.drawable.th
                "TJ" -> return R.drawable.tj
                "TK" -> return R.drawable.tk
                "TL" -> return R.drawable.tl
                "TM" -> return R.drawable.tm
                "TN" -> return R.drawable.tn
                "TO" -> return R.drawable.to
                "TR" -> return R.drawable.tr
                "TT" -> return R.drawable.tt
                "TV" -> return R.drawable.tv
                "TW" -> return R.drawable.tw
                "TZ" -> return R.drawable.tz
                "UA" -> return R.drawable.ua
                "UG" -> return R.drawable.ug
                "UY" -> return R.drawable.uy
                "US" -> return R.drawable.us
                "UZ" -> return R.drawable.uz
                "VC" -> return R.drawable.vc
                "VE" -> return R.drawable.ve
                "VG" -> return R.drawable.vg
                "VN" -> return R.drawable.vn
                "VU" -> return R.drawable.vu
                "WS" -> return R.drawable.ws
                "XK" -> return R.drawable.xk
                "YE" -> return R.drawable.ye
                "ZA" -> return R.drawable.za
                "ZM" -> return R.drawable.zm
                "ZW" -> return R.drawable.zw


            }
            return R.drawable.ic_error_black_24dp
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