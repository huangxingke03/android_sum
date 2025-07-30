package com.example.module_home.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.HandlerUtils
import com.example.common.content.PagePath
import com.example.common.ui.base.ARouterActivity
import com.example.module_home.R

/**
 * 欢迎页
 */

@Route(path = PagePath.ModuleMainPage.WELCOME_PAGE)
class WelcomeActivity : ARouterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        HandlerUtils.mainHandler().postDelayed({
            ARouter.getInstance().build(PagePath.ModuleMainPage.HOME_PAGE).navigation()
            finish()
        }, 200)
    }
}