package com.example.studyapplication.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    /**
     * 时间戳转换年月日/时分
     */
    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(timeStamp: Long,pattern:String): String {
        val format = SimpleDateFormat(pattern)
        return format.format(Date(timeStamp ))

    }
}