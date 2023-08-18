package com.example.module_function.weather.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.LogUtils
import com.example.common.net.RetrofitManager
import com.example.module_function.net.SearchWeather
import com.example.module_function.net.flow.SearchWeatherApi
import com.example.module_function.net.flow.SearchWeatherFlowApi
import com.example.module_function.net.flow.SearchWeatherFlowImpl
import com.example.module_function.weather.data.WeatherInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    var serachWeather = MutableLiveData<WeatherInfo>()
    var serachWeather1 = MutableStateFlow(WeatherInfo())
    var searchWeatherApi =
        SearchWeatherFlowImpl(RetrofitManager().getService(SearchWeatherApi::class.java))

    fun searchWeather(cityCode: String) {
        viewModelScope.launch {
//            val searchWeather =
//                RetrofitManager().getService(SearchWeather::class.java).searchWeather2(
//                    "60914564e8cb7ca7b497530783a34882", cityCode
//                )
            searchWeatherApi.searchWeather2("60914564e8cb7ca7b497530783a34882", cityCode)
                .flowOn(Dispatchers.IO).catch {
                    LogUtils.d("----------${it.message}")
                }.collect { weatherinfo ->
                    serachWeather.value = weatherinfo
                    serachWeather1.value = weatherinfo
                }
        }
//        searchWeather.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<WeatherInfo> {
//                override fun onSubscribe(d: Disposable?) {
//                }
//
//                override fun onNext(value: WeatherInfo?) {
//                    serachWeather.value = value
//                }
//
//                override fun onError(e: Throwable?) {
//                }
//
//                override fun onComplete() {
//                }
//            })
    }
}