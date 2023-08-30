package com.example.module_kotlin.ui

import android.os.Bundle
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.content.PagePath
import com.example.common.ui.base.ARouterActivity
import com.example.module_kotlin.R

@Route(path = PagePath.ModuleKotlinPage.TEST_PAGE)
class TestActivity : ARouterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        findViewById<Button>(R.id.bt_click_test).setOnClickListener {
            ARouter.getInstance().build(PagePath.ModuleKotlinPage.FUNCTION_PAGE).navigation()
        }
    }
}