package com.example.common.weather.flow

import com.example.common.weather.data.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchWeatherApi {

    @GET("v3/weather/weatherInfo")
    suspend fun searchWeather2(
        @Query("key") key: String,
        @Query("city") city: String
    ): WeatherInfo
}