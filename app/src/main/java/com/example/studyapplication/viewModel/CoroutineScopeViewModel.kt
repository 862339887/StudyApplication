package com.example.studyapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Exception

class CoroutineScopeViewModel : ViewModel() {
    companion object {
        private const val TAG = "CoroutineScopeViewModel"
    }

    var liveData = MutableLiveData<String>()

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                runBlocking {
                    getContent()
                }
                doSth()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e(TAG, "$e")
                }
            }
        }
    }

    private suspend fun getContent() {
        delay(5000)
        Log.e(TAG, "getContent:name ")
    }

    fun doSth() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.e(TAG, "打印协程任务")
        }
    }
}