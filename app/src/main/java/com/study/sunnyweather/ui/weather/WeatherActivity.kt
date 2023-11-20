package com.study.sunnyweather.ui.weather
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.study.sunnyweather.R
import com.study.sunnyweather.databinding.ActivityWeatherBinding
import com.study.sunnyweather.util.InjectorUtil
import com.study.sunnyweather.util.loge

class WeatherActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this, InjectorUtil.getWeatherViewModelFactory()).get(
        WeatherViewModel::class.java) }

    val binding by lazy { DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        binding.viewModel = viewModel
        binding.resId = R.color.colorPrimary
        binding.lifecycleOwner = this
        viewModel.weatherId = if (viewModel.isWeatherCached()) {
            viewModel.getCachedWeather().basic.weatherId
        } else {
            val str = intent.getStringExtra("weather_id").toString()
            str.loge()
            str
        }

        binding.nav.btnNav.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        viewModel.getWeather()
    }

}