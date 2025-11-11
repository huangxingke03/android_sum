package com.example.common

import java.text.SimpleDateFormat
import java.util.Date

object TimeUtils {
    fun formatTime(timestamp: Long, pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
        val sdf = SimpleDateFormat(pattern, java.util.Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}