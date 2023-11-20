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
data class City(@SerializedName("name") val cityName: String,@SerializedName("id") val cityId:Int) {
    @PrimaryKey(autoGenerate = true)
    var id2:Long = 0
    var provinceId = 0
}