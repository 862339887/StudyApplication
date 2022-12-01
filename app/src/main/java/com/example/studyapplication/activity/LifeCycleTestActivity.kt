package com.example.studyapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_life_cycle_test.*

class LifeCycleTestActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "LifeCycleTestActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle_test)
        initView()
        Log.e(TAG, "onCreate: 1111111111")
    }

    private fun initView() {
        button.setOnClickListener {
            startActivity(Intent(this, LifeCycleActivityTest1::class.java))
        }
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