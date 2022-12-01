package com.example.studyapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.studyapplication.R
import com.example.studyapplication.util.DateUtil
import kotlinx.android.synthetic.main.activity_view_test.*

class ViewTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_test)
        start.setOnClickListener {
            val result = DateUtil.getCurrentTime(System.currentTimeMillis(), "yyyy年MM月dd日 hh:mm")
            Log.d("AppCompatActivity", "onCreate: $result")
            intercept_view.setInterceptValue(true)
        }
        end.setOnClickListener {
            intercept_view.setInterceptValue(false)
        }
        show.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    EditTextActivity::class.java
                )
            )
//            Toast.makeText(this, "ViewTestActivity", Toast.LENGTH_SHORT).show()
        }
    }
}