package com.example.module_function.weather

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ViewSwitcher.ViewFactory
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.LogUtils
import com.example.common.content.PagePath
import com.example.common.net.RetrofitManager
import com.example.common.ui.ARouterActivity
import com.example.module_function.R
import com.example.module_function.net.SearchWeather
import com.example.module_function.weather.data.WeatherInfo
import com.example.module_function.weather.model.WeatherViewModel
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Route(path = PagePath.ModuleFunctionPage.WEATHER_PAGE)
class CityListActivity : ARouterActivity() {
    val weatherViewModel by lazy {
        ViewModelProvider(this).get(WeatherViewModel::class.java)
    }
    var cityPosition = 0
    var data = arrayListOf("北京", "上海", "广州", "深圳", "沈阳", "河南")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        val city_list = findViewById<ListView>(R.id.city_list)
        city_list.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                data
            )
        GlobalScope.launch(Dispatchers.Main) {
            weatherViewModel.serachWeather1.collect({
                LogUtils.d("天气信息-----------------${Gson().toJson(it)}")
                showWeather(this@CityListActivity, it, data[cityPosition])
            })
        }
        city_list.setOnItemClickListener { parent, view, position, id ->
            cityPosition = position
//            val searchWeather =
//                RetrofitManager.getInstance().getService(SearchWeather::class.java).searchWeather2(
//                    "60914564e8cb7ca7b497530783a34882", when (position) {
//                        0 -> "110000"
//                        1 -> "310000"
//                        2 -> "440100"
//                        3 -> "440300"
//                        else -> "210100"
//                    }
//                )
            weatherViewModel.searchWeather(when (position) {
                0 -> "110000"
                1 -> "310000"
                2 -> "440100"
                3 -> "440300"
                else -> "210100"
            })
//            searchWeather.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Observer<WeatherInfo> {
//                    override fun onSubscribe(d: Disposable?) {
//                        LogUtils.d("----------onSubscribe------")
//                    }
//
//                    override fun onNext(value: WeatherInfo?) {
//                        LogUtils.d("天气信息---${Gson().toJson(value)}")
//                        showWeather(this@CityListActivity, value,data[position])
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        LogUtils.d("----------onError---${e?.message}------")
//                    }
//
//                    override fun onComplete() {
//                        LogUtils.d("----------onComplete------")
//                    }
//                })
        }
    }

    fun showWeather(context: Context, weatherInfoString: WeatherInfo?, cityName: String) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(Gson().toJson(weatherInfoString?.lives?.get(0) ?: ""))
        builder.setTitle("$cityName 天气")
        val dialog = builder.create()
        dialog.show()
    }
}