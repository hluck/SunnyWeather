package com.study.sunnyweather.util

import android.util.Log

/**
 *
 * @Author： LJH
 * @Time： 2023/6/26
 * @description：通过设置currentLogLevel可设置当前应用日志级别
 */
const val TAG = "SunnyWeather"

fun String.logv(tag: String = TAG) = LogUtil.v(tag,this)
fun String.logd(tag: String = TAG) = LogUtil.d(tag,this)
fun String.logi(tag: String = TAG) = LogUtil.i(tag,this)
fun String.logw(tag: String = TAG) = LogUtil.w(tag,this)
fun String.loge(tag: String = TAG) = LogUtil.e(tag,this)

object LogUtil {
    private enum class LEVEL {
        V, D, I, W, E
    }

    //日志级别：verbose,debug,info,warn,error,默认为Verbose
    private var currentLogLevel = LEVEL.V

    fun v(tag: String,msg: String){
        if (currentLogLevel.ordinal <= LEVEL.V.ordinal){
            Log.v(tag,msg)
        }
    }

    fun d(tag: String,msg: String){
        if (currentLogLevel.ordinal <= LEVEL.D.ordinal){
            Log.d(tag,msg)
        }
    }

    fun i(tag: String,msg: String){
        if (currentLogLevel.ordinal <= LEVEL.I.ordinal){
            Log.i(tag,msg)
        }
    }

    fun w(tag: String,msg: String){
        if (currentLogLevel.ordinal <= LEVEL.W.ordinal){
            Log.w(tag,msg)
        }
    }

    fun e(tag: String,msg: String){
        if (currentLogLevel.ordinal <= LEVEL.E.ordinal){
            Log.e(tag,msg)
        }
    }
}