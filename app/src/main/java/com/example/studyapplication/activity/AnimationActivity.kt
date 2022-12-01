package com.example.studyapplication.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_animation.*
import java.util.*

class AnimationActivity : AppCompatActivity() {
    private lateinit var timer: Timer
    private lateinit var timerTask: TimerTask
    private var flag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        initView()
    }

    private fun initView() {

        start_animation_btn.setOnClickListener {
            startAudioAnimation(audio_player1, audio_player2)
        }
        test_tv.setOnClickListener {
            stopAudioAnimation()
        }

    }

    /**
     * 开启播放音频动画
     */
    private fun startAudioAnimation(imageView1: ImageView, imageView2: ImageView) {
        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                try {
                    runOnUiThread {
                        imageView1.isVisible = flag
                        imageView2.isVisible = !flag
                        flag = !flag
                    }
                } catch (e: Exception) {
                    Log.e("zhouxin-animation", e.printStackTrace().toString())
                }

            }
        }
        timer.schedule(timerTask, 200, 1000)
    }

    /**
     * 关闭播放音频动画
     */
    private fun stopAudioAnimation() {
        timer.cancel()
    }
}
