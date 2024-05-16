package com.example.rmalv4

import com.example.rmalv4.model.WeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): WeatherResponseModel
}