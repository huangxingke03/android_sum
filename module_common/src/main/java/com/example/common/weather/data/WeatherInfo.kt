package com.example.common.weather.data

data class WeatherInfo(
    var status: String? = null,
    var count: String? = null,
    var info: String? = null,
    var infocode: String? = null,
    var lives: ArrayList<LivesData>? = null,
)
data class LivesData(
    var province: String? = null,
    var city: String? = null,
    var adcode: String? = null,
    var weather: String? = null,
    var temperature: String? = null,
    var winddirection: String? = null,
    var windpower: String? = null,
    var humidity: String? = null,
    var reporttime: String? = null,
    var temperature_float: String? = null,
    var humidity_float: String? = null,
)