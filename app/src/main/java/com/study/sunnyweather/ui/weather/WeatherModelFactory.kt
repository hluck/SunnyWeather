package com.study.sunnyweather.ui.weather
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.study.sunnyweather.data.WeatherRepository


class WeatherModelFactory(private val repository: WeatherRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(repository) as T
    }
}