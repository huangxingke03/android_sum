package com.example.common

import java.util.*
import kotlin.Comparator

object StringUtils {
    /**
     * 字符串排序
     */
    fun sortString(fileNames: Array<String>): Array<String> {
        Arrays.sort(fileNames, Comparator<String> { o1, o2 ->
            val n1 = getNumber(o1)
            val n2 = getNumber(o2)
            n1 - n2
        })
        return fileNames
    }

    private fun getNumber(name: String): Int {
        return try {
            val number = name.replace("[^\\d]".toRegex(), "")
            number.toInt()
        } catch (e: Exception) {
            0
        }
    }
}