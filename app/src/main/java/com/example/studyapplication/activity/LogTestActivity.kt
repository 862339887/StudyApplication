package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_log_test.*

class LogTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_test)
        Log.e("zhouxin-create1", System.currentTimeMillis().toString() )
        button_text.setOnClickListener {
            Log.e("zhouxin-create2", System.currentTimeMillis().toString() )
            Log.e("zhouxin-test", "onCreate: " )
        }
        Log.e("zhouxin-create2", System.currentTimeMillis().toString() )
    }
}