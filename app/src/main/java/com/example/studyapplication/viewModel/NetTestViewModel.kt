package com.example.studyapplication.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyapplication.`interface`.ApiClient
import kotlinx.coroutines.launch

/**
 * Created by zhouxin on 2023/11/9
 * @author zhouxin.3012143@bytedance.com
 */


class NetTestViewModel : ViewModel() {

    fun fetchUser(id: Int) {

        viewModelScope.launch {
            try {
                val startTime = System.currentTimeMillis();
                val response = ApiClient.apiService.getUser()
                Log.e("zhouxin-response", "enter");
                if (response.isSuccessful) {
                    val user = response.body()
                    Log.e("zhouxin-response", user.toString());
                    Log.e("zhouxin-realDuration", (System.currentTimeMillis()-startTime).toString())
                    //处理用户数据...
                } else {
                    //处理错误...
                }
            } catch (e: Exception) {
                //处理异常...
                Log.e("zhouxin-response", e.toString());
            }
        }
    }
}