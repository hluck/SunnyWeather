package com.study.sunnyweather.ui.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.study.sunnyweather.data.PlaceRepository

/**
 *
 * @Author： LJH
 * @Time： 2023/7/6
 * @description：
 */
class ChooseAreaModelFactory(private val repository: PlaceRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaceViewModel(repository) as T
    }
}