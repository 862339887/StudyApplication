package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_canvas_draw.*

class CanvasDrawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_draw)
//        root.setCallBack(canvas_draw)

        clear_btn.setOnClickListener {
//            root.clear()
        }
//        draw_btn1.setOnClickListener {
//        }
//        draw_btn2.setOnClickListener {
//        }
    }
}