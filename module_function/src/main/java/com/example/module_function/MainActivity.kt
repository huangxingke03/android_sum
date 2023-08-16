package com.example.module_function

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.content.PagePath

@Route(path = PagePath.ModuleFunctionPage.MAIN_PAGE)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function_main)
        findViewById<Button>(R.id.weather_bt).setOnClickListener {
            ARouter.getInstance().build(PagePath.ModuleFunctionPage.WEATHER_PAGE)
        }
    }
}