package com.example.andoid_base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.LogUtils
import leakcanary.LeakCanary
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MyApp : Application() {
    companion object{
        // 全局线程池：IO 密集型
        @JvmStatic
        val IO_EXECUTOR by lazy {



            
        }

    }
    override fun onCreate() {
        super.onCreate()
        leakCanaryConfig()
        LogUtils.init()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    fun instance(): Application = this

    private fun leakCanaryConfig() {
        //App 处于前台时检测保留对象的阈值，默认是 5
        LeakCanary.config = LeakCanary.config.copy(retainedVisibleThreshold = 3)
        //自定义要检测的保留对象类型，默认监测 Activity，Fragment，FragmentViews 和 ViewModels
//        AppWatcher.config= AppWatcher.config.copy(watchFragmentViews = false)
        //隐藏泄漏显示活动启动器图标，默认为 true
        LeakCanary.showLeakDisplayActivityLauncherIcon(false)
    }
}