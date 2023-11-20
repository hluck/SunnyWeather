package com.study.sunnyweather.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.study.sunnyweather.R
import com.study.sunnyweather.ui.place.ChooseAreaFragment
import com.study.sunnyweather.ui.weather.WeatherActivity
import com.study.sunnyweather.util.InjectorUtil
import com.study.sunnyweather.util.logd

class MainActivity : AppCompatActivity() {

    private val mainViewModel:MainViewModel by lazy {
        ViewModelProviders.of(this,InjectorUtil.getMainModelFactory()).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (mainViewModel.isWeatherCached()){
            startActivity(Intent(this,WeatherActivity::class.java))
            finish()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.container,ChooseAreaFragment()).commit()
        }
    }


}