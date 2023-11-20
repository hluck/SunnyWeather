package com.study.sunnyweather

import android.app.Application
import android.content.Context

/**
 *
 * @Author： LJH
 * @Time： 2023/6/26
 * @description：
 * 彩云天气api：https://dashboard.caiyunapp.com/
 * https://api.caiyunapp.com/v2/place?query=北京&token={t7pwG77cluS2EY2q}&lang=zh_CN
 */
class App: Application() {

    companion object{
        lateinit var appContext:Context
        const val token:String = "t7pwG77cluS2EY2q"
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}