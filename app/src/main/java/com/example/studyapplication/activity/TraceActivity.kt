package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.studyapplication.R
import com.example.studyapplication.Trace

class TraceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace2)
        Log.e("zhouxin-testTrace", "fdsf");
        myMethod()
    }


    @Trace
    fun myMethod() {
        //模拟耗时操作
        Thread.sleep(1000)
    }
}