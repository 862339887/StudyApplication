package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_timer_test.*
import java.util.*

class TimerTestActivity : AppCompatActivity() {
    private var timer: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_test)

        val testList = arrayListOf("a", "b", "g", "f","d","k","a","e")
        cancel_timer.setOnClickListener {
            testList.sortBy { it.toCharArray()[0] }
            testList.reversed()
            testList.forEach {
                println(it)
            }
        }

        cancel_timer2.setOnClickListener {
            timer = Timer()
            timer!!.schedule(object : TimerTask() {
                override fun run() {
                    text_test.text = "延时改变2"
                }

            }, 1000)
        }
        if (timer == null) {
            timer = Timer()
        }
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                text_test.text = "延时改变"
            }

        }, 6000)
    }
}