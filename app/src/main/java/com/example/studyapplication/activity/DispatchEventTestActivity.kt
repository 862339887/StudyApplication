package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.MotionEvent
import android.widget.Toast
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_dispatch_event_test.*

class DispatchEventTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispatch_event_test)
        initView()
    }

    private fun initView() {
        textview.setOnClickListener {
            Toast.makeText(this, "fdfafdaf", Toast.LENGTH_LONG).show()
        }

        root.setOnClickListener {
            Toast.makeText(this, "root", Toast.LENGTH_LONG).show()
        }
    }

//    override fun onTouchEvent(event: MotionEvent?): Boolean {
////        return super.onTouchEvent(event)
//        return true
//    }
//
//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        return true
////        return super.dispatchTouchEvent(ev)
//    }


//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return false
////        return super.onTouchEvent(event)
//    }
}