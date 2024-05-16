package com.example.rmalv4.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmalv4.OpenWeatherApiService
import com.example.rmalv4.model.WeatherDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Timer
import kotlin.concurrent.timerTask
import kotlin.math.roundToInt

class WeatherViewModel(): ViewModel() {

    private var timer: Timer? = null
    private val latitude = 45.55111
    private val longitude = 18.69389
    private val apiKey = "22e499fca49c958764d7682de1f85a6a"
    // Ensuring startFetching is only called once
    private var isFetchingStarted = false

    private val retrofit: Retrofit? = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val openWeatherApiService: OpenWeatherApiService? = retrofit?.create(OpenWeatherApiService::class.java)


    private val _weatherData = MutableLiveData<WeatherDataModel>()
    val weatherData: LiveData<WeatherDataModel> = _weatherData

    fun startFetching(){
        if(!isFetchingStarted){
            isFetchingStarted = true
            timer = Timer()
            timer?.schedule(timerTask {
                fetchWeather()
            },1000,60000) // Update every second
        }
    }

    fun stopFetching(){
        timer?.cancel()
    }

    private fun fetchWeather(){
        viewModelScope.launch {
            val weatherResponse = withContext(Dispatchers.IO) {
                openWeatherApiService?.getWeather(latitude, longitude, apiKey)
            }
            Log.d("FETCHED DATA", weatherResponse.toString())
            val main = weatherResponse?.weather?.getOrNull(0)?.main ?: ""
            val description = weatherResponse?.weather?.getOrNull(0)?.description ?: ""
            var temp = weatherResponse?.main?.temp ?: 0.0
            if(temp != 0.0){
                temp -= 273.15
                temp = temp.roundToInt().toDouble()
            }
            _weatherData.postValue(WeatherDataModel(main, description, temp))
        }
    }

}