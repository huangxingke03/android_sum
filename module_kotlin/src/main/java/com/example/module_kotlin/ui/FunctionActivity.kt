package com.example.module_kotlin.ui

import android.os.Bundle
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.content.PagePath
import com.example.common.ui.base.ARouterActivity
import com.example.module_kotlin.R
@Route(path=PagePath.ModuleKotlinPage.FUNCTION_PAGE)
class FunctionActivity : ARouterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function)
        findViewById<Button>(R.id.bt_click_function).setOnClickListener {
            ARouter.getInstance().build(PagePath.ModuleKotlinPage.TEST_PAGE).navigation()
        }
    }
}