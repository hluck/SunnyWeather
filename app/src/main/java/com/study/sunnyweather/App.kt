package com.study.sunnyweather

import android.app.Application
import android.content.Context

/**
 *
 * @Author： LJH
 * @Time： 2023/6/26
 * @description：
 */
class App: Application() {

    companion object{
        lateinit var appContext:Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}