package com.study.sunnyweather.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.study.sunnyweather.data.model.place.City
import com.study.sunnyweather.data.model.place.County
import com.study.sunnyweather.data.model.place.Province

/**
 *
 * @Author： LJH
 * @Time： 2023/7/4
 * @description：
 */
@Dao
interface PlaceDao {

    @Query("select * from Province")
    fun getProvinceList():MutableList<Province>

    @Query("select * from City where provinceId = :provinceId")
    fun getCityList(provinceId:Int):MutableList<City>

    @Query("select * from County where cityId = :cityId")
    fun getCountyList(cityId:Int):MutableList<County>

    @Insert
    fun insertProvinceList(provinceList:MutableList<Province>)

    @Insert
    fun insertCityList(cityList:MutableList<City>)

    @Insert
    fun insertCountyList(countyList:MutableList<County>)
}