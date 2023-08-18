package com.example.module_function.net.flow

import com.example.module_function.weather.data.WeatherInfo
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchWeatherFlowApi {
    @GET("v3/weather/weatherInfo")
    fun searchWeather2(
        @Query("key") key: String,
        @Query("city") city: String
    ): Flow<WeatherInfo>
}