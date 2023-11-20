package com.study.sunnyweather.data.model.weather
import com.google.gson.annotations.SerializedName
import com.study.sunnyweather.data.model.weather.Weather

class HeWeather {

    @SerializedName("HeWeather")
    var weather: List<Weather>? = null

}