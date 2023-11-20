package com.study.sunnyweather.ui.place

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.sunnyweather.App
import com.study.sunnyweather.data.PlaceRepository
import com.study.sunnyweather.data.model.place.City
import com.study.sunnyweather.data.model.place.County
import com.study.sunnyweather.data.model.place.Province
import kotlinx.coroutines.launch

/**
 *
 * @Author： LJH
 * @Time： 2023/7/5
 * @description：
 */
class PlaceViewModel(private val placeRepository: PlaceRepository):ViewModel() {

    //标题等级
    private val _currentLevel = MutableLiveData<Int>()
    val currentLevel: LiveData<Int> get() = _currentLevel

    //列表数据变化
    private val _dataChanged = MutableLiveData<Int>()
    val dataChanged: LiveData<Int> get() = _dataChanged

    //标题等级
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    //区域选择
    val _areaSelected = MutableLiveData<Boolean>()
    val areaSelected: LiveData<Boolean> get() = _areaSelected

    var selectedProvince: Province? = null
    var selectedCity: City? = null
    var selectedCounty: County? = null

    //查询到的省列表
    lateinit var provinces:MutableList<Province>
    //查询到的城市列表
    lateinit var cities:MutableList<City>
    //查询到的县
    lateinit var counties:MutableList<County>

    //城市列表数据
    val dataList = ArrayList<String>()

    fun getProvinces(){
        _currentLevel.value = ChooseAreaFragment.LEVEL_PROVINCE
        launch {
            provinces = placeRepository.getProvinceList()
            dataList.addAll(provinces.map { it.provinceName })
        }
    }

    private fun getCities() = selectedProvince?.let {
        _currentLevel.value = ChooseAreaFragment.LEVEL_CITY
        launch {
            cities = placeRepository.getCityList(it.provinceId)
            dataList.addAll(cities.map { it.cityName })
        }
    }

    private fun getCounties() = selectedCity?.let {
        _currentLevel.value = ChooseAreaFragment.LEVEL_COUNTY
        launch {
            counties = placeRepository.getCountyList(it.provinceId,it.cityId)
            dataList.addAll(counties.map { it.countyName })
        }
    }

    fun onListViewItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long){
        when{
            currentLevel.value == ChooseAreaFragment.LEVEL_PROVINCE -> {
                selectedProvince = provinces[position]
                getCities()
            }
            currentLevel.value == ChooseAreaFragment.LEVEL_CITY -> {
                selectedCity = cities[position]
                getCounties()
            }
            currentLevel.value == ChooseAreaFragment.LEVEL_COUNTY -> {
                selectedCounty = counties[position]
                _areaSelected.value = true
            }
        }
    }

    fun onBack(){
        if (currentLevel.value == ChooseAreaFragment.LEVEL_COUNTY){
            getCities()
        } else if (currentLevel.value == ChooseAreaFragment.LEVEL_CITY){
            getProvinces()
        }
    }

    private fun launch(block:suspend () -> Unit) = viewModelScope.launch {
        try {
            _isLoading.value = true
            dataList.clear()
            block()
            _dataChanged.value = _dataChanged.value?.plus(1)
            _isLoading.value = false
        } catch (t:Throwable) {
            t.printStackTrace()
            Toast.makeText(App.appContext,t.message,Toast.LENGTH_SHORT).show()
            _dataChanged.value = _dataChanged.value?.plus(1)
            _isLoading.value = false
        }
    }

}