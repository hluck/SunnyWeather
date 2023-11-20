package com.study.sunnyweather.data.db

import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.study.sunnyweather.App
import com.study.sunnyweather.data.model.weather.Weather

/**
 *
 * @Author： LJH
 * @Time： 2023/7/4
 * @description：
 */
class WeatherDao {

    private val sp = PreferenceManager.getDefaultSharedPreferences(App.appContext)

    fun cacheWeatherInfo(weather: Weather?){
        if (weather == null) return
        sp.edit {
            val weatherInfo = Gson().toJson(weather)
            putString("weather", weatherInfo)
        }
    }

    fun getWeatherInfo(): Weather?{
        val weatherInfo = sp.getString("weather",null)
        if (weatherInfo != null){
            return Gson().fromJson(weatherInfo,Weather::class.java)
        }
        return null
    }

    fun cacheBingPic(bingPic:String?){
        if (bingPic == null) return
        sp.edit {
            putString("bing_pic",bingPic)
        }
    }

    fun getCachedBingPic():String? = sp.getString("bing_pic",null)

}