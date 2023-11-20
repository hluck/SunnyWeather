package com.study.sunnyweather.data.model.place

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 *
 * @Author： LJH
 * @Time： 2023/7/4
 * @description：
 */
@Entity
data class County(@SerializedName("name") val countyName: String, @SerializedName("weather_id") val weatherId:String){

    @PrimaryKey(autoGenerate = true)
    var id2:Long = 0

    var cityId = 0

}
