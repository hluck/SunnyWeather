package com.study.sunnyweather.data.network.api

import com.study.sunnyweather.data.network.ServiceCreator
import com.study.sunnyweather.util.logd
import com.study.sunnyweather.util.loge
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *
 * @Author： LJH
 * @Time： 2023/7/4
 * @description：
 */
class SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)
    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun fetchProvinceList() = placeService.getProvinces().await()
    suspend fun fetchCityList(provinceId:Int) = placeService.getCities(provinceId).await()
    suspend fun fetchCountyList(provinceId:Int,cityId:Int) = placeService.getCounties(provinceId,cityId).await()
    suspend fun fetchWeather(weatherId:String) = weatherService.getWeather(weatherId).await()
    suspend fun fetchBingPic() = weatherService.getBingPic().await()

    private suspend fun <T> Call<T>.await():T {
        return suspendCoroutine { continuation ->
            enqueue(object :Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    "fail".logd()
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    companion object{
        private var network:SunnyWeatherNetwork? = null

        fun getInstance():SunnyWeatherNetwork{
            if (network == null){
                synchronized(SunnyWeatherNetwork::class.java){
                    if (network == null){
                        network = SunnyWeatherNetwork()
                    }
                }
            }
            return network!!
        }
    }
}