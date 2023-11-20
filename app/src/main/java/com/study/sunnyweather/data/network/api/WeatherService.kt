package com.study.sunnyweather.data.network.api

import com.study.sunnyweather.data.model.weather.HeWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @Author： LJH
 * @Time： 2023/7/4
 * @description：
 */
interface WeatherService {

    @GET("api/weather")
    fun getWeather(@Query("cityid") weatherId: String):Call<HeWeather>

    @GET("https://api.likepoems.com/img/nature")
    fun getBingPic():Call<String>
}