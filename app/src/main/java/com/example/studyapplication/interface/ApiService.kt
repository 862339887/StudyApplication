package com.example.studyapplication.`interface`

import com.example.studyapplication.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response


/**
 * Created by zhouxin on 2023/11/9
 * @author zhouxin.3012143@bytedance.com
 */
interface ApiService {
    @GET("user/list")
    suspend fun getUser(): Response<User>
}