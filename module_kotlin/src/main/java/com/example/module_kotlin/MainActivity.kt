package com.example.module_kotlin

import android.os.Bundle
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.content.PagePath
import com.example.common.ui.base.ARouterActivity


@Route(path = PagePath.ModuleKotlinPage.MAIN_PAGE)
class MainActivity : ARouterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        findViewById<Button>(R.id.bt_click_main).setOnClickListener {
            ARouter.getInstance().build(PagePath.ModuleKotlinPage.FUNCTION_PAGE).navigation()
        }
    }
}