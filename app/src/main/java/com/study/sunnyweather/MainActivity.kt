package com.study.sunnyweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.sunnyweather.util.logd
import com.study.sunnyweather.util.loge

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        "onCreate".logd()
    }
}