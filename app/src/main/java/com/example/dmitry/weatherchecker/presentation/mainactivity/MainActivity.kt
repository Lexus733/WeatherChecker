package com.example.dmitry.weatherchecker.presentation.mainactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.dmitry.weatherchecker.R

class MainActivity : MvpAppCompatActivity(), IMainActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
