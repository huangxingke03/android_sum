package com.example.common.net

import okhttp3.*
import okio.IOException

class OkhttpManager {
    init {
        val okHttpClient = OkHttpClient.Builder().build()
        val request = Request.Builder().url("").build()
        val newCall = okHttpClient.newCall(request)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                TODO("Not yet implemented")
            }

        })

        val execute = newCall.execute()
        val string = execute.body.string()
    }
}