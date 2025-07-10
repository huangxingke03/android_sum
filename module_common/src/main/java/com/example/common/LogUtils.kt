package com.example.common

import com.orhanobut.logger.*
import java.util.*

/**
 *  implementation 'com.orhanobut:logger:2.2.0'
 *
 */
object LogUtils {
    fun init() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(0) // (Optional) How many method line to show. Default 2
            .methodOffset(3) // (Optional) Skips some method invokes in stack trace. Default 5
            //        .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
            .tag("hxk_log") // (Optional) Custom tag for each log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    fun <T> d(content: T) {
        Logger.d(content)
    }

    @JvmStatic
    fun d(content: String) {
        Logger.d(content)
    }

    fun d(message: String, vararg args: Any) {
        Logger.d(message, * args)
    }

    fun d(arrayList: ArrayList<Any>) {
        Logger.d(arrayList)
    }


    fun list(content: Any) {
        Logger.d(content)
    }


}