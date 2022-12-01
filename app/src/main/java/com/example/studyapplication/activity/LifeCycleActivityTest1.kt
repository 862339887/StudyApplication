package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.studyapplication.R

class LifeCycleActivityTest1 : AppCompatActivity() {
    companion object {
        private const val TAG = "LifeCycleActivityTest1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_test1)
        Log.e(TAG, "onCreate: 1111111111")

    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: 1111111111")

    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: 1111111111")

    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: 1111111111")

    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: 1111111111")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: 1111111111")
    }
}