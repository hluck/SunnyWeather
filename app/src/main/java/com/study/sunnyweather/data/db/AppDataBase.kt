package com.study.sunnyweather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.study.sunnyweather.App
import com.study.sunnyweather.data.model.place.City
import com.study.sunnyweather.data.model.place.County
import com.study.sunnyweather.data.model.place.Province

/**
 *
 * @Author： LJH
 * @Time： 2023/7/4
 * @description：
 */
@Database(version = 1,entities = [Province::class,City::class,County::class])
abstract class AppDataBase:RoomDatabase() {

    abstract fun placeDao():PlaceDao

    private var weatherDao: WeatherDao? = null

    companion object{
        private var instance:AppDataBase? = null

        @Synchronized
        fun getDatabase(context: Context):AppDataBase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(App.appContext,AppDataBase::class.java,"app_database")
                .build().apply {
                    instance = this
                }
        }
    }

    fun getWeatherDao():WeatherDao{
        if (weatherDao == null) weatherDao = WeatherDao()
        return weatherDao!!
    }
}