package com.example.common.weather.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.LogUtils
import com.example.common.net.RetrofitManager
import com.example.common.weather.SearchWeather
import com.example.common.weather.data.WeatherInfo
import com.example.common.weather.flow.SearchWeatherApi
import com.example.common.weather.flow.SearchWeatherFlowImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.getValue

class WeatherViewModel : ViewModel() {
    var serachWeather = MutableLiveData<WeatherInfo>()
    var serachWeather1 = MutableStateFlow(WeatherInfo())
    var searchWeatherApi =
        SearchWeatherFlowImpl(RetrofitManager().getService(SearchWeatherApi::class.java))
    private val searchWeather by lazy {
        RetrofitManager().getService(SearchWeather::class.java)
    }

    fun searchWeatherFlow(cityCode: String) {
        viewModelScope.launch {
            searchWeatherApi.searchWeather2("60914564e8cb7ca7b497530783a34882", cityCode)
                .flowOn(Dispatchers.IO).catch {
                    LogUtils.d("----------${it.message}")
                }.collect { weatherinfo ->
                    serachWeather.value = weatherinfo
                    serachWeather1.value = weatherinfo
                }
        }
    }

    fun searchWeatherObservable(cityCode: String) {
//        val searchWeather =
//            searchWeather.searchWeather1("60914564e8cb7ca7b497530783a34882", cityCode)
//        searchWeather
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<WeatherInfo> {
//                override fun onSubscribe(d: Disposable?) {
//                    LogUtils.d("--------Observable---onSubscribe--")
//                }
//
//                override fun onNext(value: WeatherInfo?) {
//                    serachWeather.value = value
//                    LogUtils.d("--------Observable---onNext---$value")
//                }
//
//                override fun onError(e: Throwable?) {
//                    LogUtils.d("--------Observable---onError---${e?.message}")
//                }
//
//                override fun onComplete() {
//                    LogUtils.d("--------Observable---onComplete---")
//                }
//            })
    }

    fun searchWeatherCall(cityCode: String) {
        val searchWeather =
            searchWeather.searchWeather("60914564e8cb7ca7b497530783a34882", cityCode)
        searchWeather.enqueue(object : Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                LogUtils.d("--------call---onResponse---${response.body().toString()}")
            }

            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                LogUtils.d("--------call---onFailure---${t.message}")
            }

        })
    }

    fun test() {
        Observable.create(ObservableOnSubscribe<String> { emitter ->
            emitter.onNext("test1")
            emitter.onNext("test2")
            LogUtils.d("--------test----被观察者创建-----")
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    LogUtils.d("--------onSubscribe----观察者-----")
                }

                override fun onNext(t: String) {
                    LogUtils.d("--------onNext----观察者-----t=$t---")
                }

                override fun onError(e: Throwable) {
                    LogUtils.d("--------onError----观察者-----")
                }

                override fun onComplete() {
                    LogUtils.d("--------onComplete----观察者-----")
                }
            })
    }
}