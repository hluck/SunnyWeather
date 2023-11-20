package com.study.sunnyweather.ui

import androidx.lifecycle.ViewModel
import com.study.sunnyweather.data.WeatherRepository

/**
 *
 * @Author： LJH
 * @Time： 2023/7/6
 * @description：
 */
class MainViewModel(private val weatherRepository: WeatherRepository):ViewModel() {

    fun isWeatherCached(): Boolean = weatherRepository.isWeatherCached()
}