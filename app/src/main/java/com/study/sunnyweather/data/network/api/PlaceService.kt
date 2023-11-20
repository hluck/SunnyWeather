package com.study.sunnyweather.data.network.api

import com.study.sunnyweather.data.model.place.City
import com.study.sunnyweather.data.model.place.County
import com.study.sunnyweather.data.model.place.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @Author： LJH
 * @Time： 2023/7/4
 * @description： http://guolin.tech/
 */
interface PlaceService {

    @GET("api/china")
    fun getProvinces():Call<MutableList<Province>>

    @GET("api/china/{provinceId}")
    fun getCities(@Path("provinceId") provinceId:Int):Call<MutableList<City>>

    @GET("api/china/{provinceId}/{cityId}")
    fun getCounties(@Path("provinceId") provinceId:Int,@Path("cityId") cityId:Int):Call<MutableList<County>>
}