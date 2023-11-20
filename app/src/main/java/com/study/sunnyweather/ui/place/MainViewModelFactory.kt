package com.study.sunnyweather.ui.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.study.sunnyweather.data.WeatherRepository
import com.study.sunnyweather.data.model.place.County
import com.study.sunnyweather.ui.MainViewModel

/**
 *
 * @Author： LJH
 * @Time： 2023/7/6
 * @description：
 */
class MainViewModelFactory(private val weatherRepository: WeatherRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(weatherRepository) as T
    }
}