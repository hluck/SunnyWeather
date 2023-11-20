package com.study.sunnyweather.data

import com.study.sunnyweather.data.db.WeatherDao
import com.study.sunnyweather.data.model.weather.Weather
import com.study.sunnyweather.data.network.api.SunnyWeatherNetwork
import com.study.sunnyweather.util.logd
import com.study.sunnyweather.util.loge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 *
 * @Author： LJH
 * @Time： 2023/7/5
 * @description：
 */
class WeatherRepository private constructor(private val weatherDao:WeatherDao,private val network:SunnyWeatherNetwork){

    suspend fun getWeather(weatherId: String):Weather{
        var weather = weatherDao.getWeatherInfo()
        "从网络获取Weather".logd()
        if (weather == null) weather = requestWeather(weatherId)
        "网络请求执行完了".loge()
        return weather
    }

    suspend fun refreshWeather(weatherId: String) = requestWeather(weatherId)

    suspend fun getBingPic():String {
        var url = weatherDao.getCachedBingPic()
        if (url == null) url = requestBingPic()
        return url
    }

    suspend fun refreshBingPic() = requestBingPic()

    fun isWeatherCached() = weatherDao.getWeatherInfo() != null

    fun getCachedWeatherInfo() = weatherDao.getWeatherInfo()!!

    private suspend fun requestBingPic() = withContext(Dispatchers.IO){
        val url = network.fetchBingPic()
        weatherDao.cacheBingPic(url)
        url
    }

    private suspend fun requestWeather(weatherId:String) = withContext(Dispatchers.IO){
        "weatherId:$weatherId".logd()
        val heWeather = network.fetchWeather(weatherId)
        "加个5秒延时".loge()
        try {
            Thread.sleep(5000)
            "延时结束".logd()
        } catch (e:Exception){
            e.printStackTrace()
        }
        val weather = heWeather.weather!![0]
        weatherDao.cacheWeatherInfo(weather)
        weather
    }

    companion object{
        private lateinit var instance:WeatherRepository

        fun getInstance(weatherDao: WeatherDao,network:SunnyWeatherNetwork):WeatherRepository{
            if (!::instance.isInitialized){
                synchronized(WeatherRepository::class.java){
                    if (!::instance.isInitialized){
                        instance = WeatherRepository(weatherDao, network)
                    }
                }
            }
            return instance
        }

    }
}