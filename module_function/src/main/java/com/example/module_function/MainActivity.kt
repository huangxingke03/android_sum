package com.example.module_function

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.ARouterUtils
import com.example.common.content.PagePath
import com.example.common.content.PagePath.ModuleFunctionPage.WEATHER_PAGE

@Route(path = PagePath.ModuleFunctionPage.MAIN_PAGE)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function_main)
        findViewById<Button>(R.id.weather_bt).setOnClickListener {
            ARouterUtils.navigationPage(WEATHER_PAGE)
        }
    }
}