package com.example.studyapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_for_result.*

class ActivityForResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_result)
        button.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, TestActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivityForResult(intent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
//            val message = intent.get("aaaa")
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}