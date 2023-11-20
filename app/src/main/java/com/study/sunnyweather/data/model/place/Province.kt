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
data class Province(@SerializedName("name") val provinceName:String,@SerializedName("id") val provinceId:Int){
    @PrimaryKey(autoGenerate = true)
    var id2:Long = 0
}
