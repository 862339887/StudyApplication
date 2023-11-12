package com.example.studyapplication.`interface`

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by zhouxin on 2023/11/9
 * @author zhouxin.3012143@bytedance.com
 */
class ApiClient {
    companion object {
        private const val BASE_URL = "https://mock.apifox.com/m1/3566617-0-default/"
        private val retrofit: Retrofit

        init {
            val okHttpClient = OkHttpClient.Builder()
                .callTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(NetWorkMonitorIntercept())
                // 更多设置...
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())

                .build()
        }

        val apiService: ApiService by lazy { //延迟初始化ApiService
            retrofit.create(ApiService::class.java)
        }
    }
}