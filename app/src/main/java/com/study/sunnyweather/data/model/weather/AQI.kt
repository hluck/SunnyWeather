package com.study.sunnyweather.data.model.weather

/**
 *
 * @Author： LJH
 * @Time： 2023/7/4
 * @description：
 */
class AQI {
    lateinit var city:AQICity

    inner class AQICity{
        var aqi = ""
        var pm25 = ""
    }
}