package com.study.sunnyweather.data

import com.study.sunnyweather.data.db.PlaceDao
import com.study.sunnyweather.data.model.place.Province
import com.study.sunnyweather.data.network.api.SunnyWeatherNetwork
import com.study.sunnyweather.util.loge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * @Author： LJH
 * @Time： 2023/7/5
 * @description：
 */
class PlaceRepository private constructor(private val placeDao: PlaceDao,private val network: SunnyWeatherNetwork){

    suspend fun getProvinceList() = withContext(Dispatchers.IO) {
        var list = placeDao.getProvinceList()
        if (list.isEmpty()){
            list = network.fetchProvinceList()
            placeDao.insertProvinceList(list)
        }
        list
    }

    suspend fun getCityList(provinceId:Int) = withContext(Dispatchers.IO){
        var list = placeDao.getCityList(provinceId)
        if (list.isEmpty()){
            list = network.fetchCityList(provinceId)
            list.forEach { it.provinceId = provinceId }
            placeDao.insertCityList(list)
        }
        list
    }

    suspend fun getCountyList(provinceId: Int,cityId:Int) = withContext(Dispatchers.IO){
        var list = placeDao.getCountyList(cityId)
        if (list.isEmpty()){
            list = network.fetchCountyList(provinceId,cityId)
            list.forEach { it.cityId = cityId }
            placeDao.insertCountyList(list)
        }
        list
    }

    companion object{
        private var instance:PlaceRepository? = null
        fun getInstance(placeDao: PlaceDao,network: SunnyWeatherNetwork):PlaceRepository{
            if (instance == null){
                synchronized(PlaceRepository::class.java){
                    if (instance == null){
                        instance = PlaceRepository(placeDao,network)
                    }
                }
            }
            return instance!!
        }
    }

}