package com.example.common.weather.flow

import com.example.common.weather.data.WeatherInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchWeatherFlowApi {
    @GET("v3/weather/weatherInfo")
    fun searchWeather2(
        @Query("key") key: String,
        @Query("city") city: String
    ): Flow<WeatherInfo>
}