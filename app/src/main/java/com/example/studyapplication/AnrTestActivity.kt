package com.example.studyapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

class AnrTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anr_test)

        Handler(Looper.getMainLooper()).post {
            Looper.loop()
            // 执行计算任务
            val result = 1 + 1
            Log.d("zhouxin-AnrTestActivity", "Result: $result")
        }


        Thread {
            Looper.prepare()
            Looper.loop()

            // 执行计算任务
            val result = 1 + 1
            Log.d("zhouxin-Thread", "Result: $result")
        }.start()
    }
}