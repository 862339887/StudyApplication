package com.example.studyapplication.`interface`

import okhttp3.Interceptor
import java.io.IOException
import okhttp3.Response
import android.util.Log

/**
 * Created by zhouxin on 2023/11/10
 * @author zhouxin.3012143@bytedance.com
 */

class NetWorkMonitorIntercept : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // 记录请求开始的时间
        val startTime = System.currentTimeMillis()

        val response: Response
        try {
            // 发出请求
            response = chain.proceed(request)
        } catch (e: Exception) {
            // 如果请求失败，你可以在这里记录失败的请求
            Log.d("MonitoringInterceptor", "Request failed: ${e.message}")
            throw e
        }

        // 记录请求结束的时间，并计算耗时
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        Log.e("zhouxin-duration" , duration.toString())

        if (response.isSuccessful) {
            // 如果请求成功，你可以在这里记录成功的请求和它的耗时
            Log.d("MonitoringInterceptor", "Request successful, took ${duration}ms")
        } else {
            // 如果请求未成功（例如，服务器返回了错误的 HTTP 状态码），你可以在这里记录未成功的请求
            Log.d("MonitoringInterceptor", "Request unsuccessful, took ${duration}ms")
        }

        return response
    }
}