package com.study.sunnyweather.ui.weather
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.sunnyweather.App
import com.study.sunnyweather.data.WeatherRepository
import com.study.sunnyweather.data.model.weather.Weather
import com.study.sunnyweather.util.loge
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    val test = "我亦无他"

    var weather = MutableLiveData<Weather>()

    var currentWeather:Weather? = null

    var bingPicUrl = MutableLiveData<String>()

    var refreshing = MutableLiveData<Boolean>()

    var weatherInitialized = MutableLiveData<Boolean>()

    var weatherId = ""

    fun getWeather() {
        launch ({
            weather.value = repository.getWeather(weatherId)
            currentWeather = weather.value
            "getWeather() -> ${currentWeather?.basic?.cityName}".loge()
            weatherInitialized.value = true
        }, {
            Toast.makeText(App.appContext, it.message, Toast.LENGTH_SHORT).show()
        })
        getBingPic(false)
    }

    fun refreshWeather() {
        refreshing.value = true
        launch ({
            weather.value = repository.refreshWeather(weatherId)
            currentWeather = weather.value
            "refreshWeather() -> ${currentWeather?.basic?.cityName}".loge()
            refreshing.value = false
            weatherInitialized.value = true
        }, {
            Toast.makeText(App.appContext, it.message, Toast.LENGTH_SHORT).show()
            refreshing.value = false
        })
        getBingPic(true)
    }

    fun isWeatherCached() = repository.isWeatherCached()

    fun getCachedWeather() = repository.getCachedWeatherInfo()

    fun onRefresh() {
        refreshWeather()
    }

    private fun getBingPic(refresh: Boolean) {
        launch({
//            bingPicUrl.value = if (refresh) repository.refreshBingPic() else repository.getBingPic()
            bingPicUrl.value = "https://api.likepoems.com/img/nature"
        }, {
            Toast.makeText(App.appContext, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = viewModelScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
        }
    }

}