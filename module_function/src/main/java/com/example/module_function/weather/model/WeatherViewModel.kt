package com.example.module_function.weather.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.net.RetrofitManager
import com.example.module_function.net.SearchWeather
import com.example.module_function.weather.data.WeatherInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherViewModel : ViewModel() {
    var serachWeather = MutableLiveData<WeatherInfo>()

    fun searchWeather(cityCode: String) {
        val searchWeather = RetrofitManager().getService(SearchWeather::class.java).searchWeather1(
            "60914564e8cb7ca7b497530783a34882", cityCode
        )
        searchWeather.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<WeatherInfo> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(value: WeatherInfo?) {
                    serachWeather.value = value
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {
                }
            })
    }
}