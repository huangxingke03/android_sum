package com.example.common

import com.alibaba.android.arouter.launcher.ARouter

object ARouterUtils {
    fun navigationPage(pagePath: String) {
        ARouter.getInstance().build(pagePath).navigation()
    }
}