package com.study.sunnyweather.util

import com.study.sunnyweather.App
import com.study.sunnyweather.data.PlaceRepository
import com.study.sunnyweather.data.WeatherRepository
import com.study.sunnyweather.data.db.AppDataBase
import com.study.sunnyweather.data.network.api.SunnyWeatherNetwork
import com.study.sunnyweather.ui.place.ChooseAreaModelFactory
import com.study.sunnyweather.ui.place.MainViewModelFactory
import com.study.sunnyweather.ui.weather.WeatherModelFactory

/**
 *
 * @Author： LJH
 * @Time： 2023/7/6
 * @description：
 */
object InjectorUtil {

    private fun getPlaceRepository() = PlaceRepository.getInstance(AppDataBase.getDatabase(App.appContext).placeDao(),
        SunnyWeatherNetwork.getInstance())

    private fun getWeatherRepository() = WeatherRepository.getInstance(AppDataBase.getDatabase(App.appContext).getWeatherDao(),
        SunnyWeatherNetwork.getInstance())

    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())
    fun getMainModelFactory() = MainViewModelFactory(getWeatherRepository())
    fun getWeatherViewModelFactory() = WeatherModelFactory(getWeatherRepository())
}