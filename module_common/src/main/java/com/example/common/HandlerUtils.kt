package com.example.common

import android.os.Handler
import android.os.Handler.Callback
import android.os.Looper

object HandlerUtils {
    fun mainHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }

    fun handler(looper: Looper): Handler {
        return Handler(looper)
    }

    fun handler(looper: Looper, callBack: Callback): Handler {
        return Handler(looper, callBack)
    }
}