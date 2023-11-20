package com.study.sunnyweather.data.model.weather

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.study.sunnyweather.BR


/**
 *
 * @Author： LJH
 * @Time： 2023/7/4
 * @description：
 */
class Basic:BaseObservable() {

    @SerializedName("city")
    var cityName:String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weather)
        }
        @Bindable
        get() = field
    @SerializedName("id")
    var weatherId:String = ""

    lateinit var update:Update

    inner class Update{
        @SerializedName("loc")
        var updateTime = ""

        fun time() = updateTime.split(" ")[1]
    }
}