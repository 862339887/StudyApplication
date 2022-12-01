package com.example.studyapplication.activity

import android.graphics.Paint
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_measure_text11.*

class MeasureText11Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure_text11)
        initView()
    }

    private fun initView() {
        change_btn.setOnClickListener {
            val mPaint = change_tv.paint
            val testText = "42343243fwafafgfadfafafafa"
            val textWidth: Float = mPaint.measureText(testText, 0, testText.length)
            val layoutParams =
                LinearLayout.LayoutParams(textWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.gravity=Gravity.CENTER_HORIZONTAL
            change_tv.layoutParams = layoutParams
            change_tv.text=testText
        }
    }
}