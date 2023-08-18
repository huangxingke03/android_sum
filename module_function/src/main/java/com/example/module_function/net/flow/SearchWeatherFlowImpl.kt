package com.example.module_function.net.flow

import kotlinx.coroutines.flow.flow

class SearchWeatherFlowImpl(private val searchWeatherApi: SearchWeatherApi) : SearchWeatherFlowApi {
    override fun searchWeather2(key: String, city: String) = flow {
        emit(searchWeatherApi.searchWeather2(key, city))
    }
}