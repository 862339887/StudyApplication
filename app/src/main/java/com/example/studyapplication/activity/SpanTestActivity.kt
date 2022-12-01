package com.example.studyapplication.activity

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_span_test.*


class SpanTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_span_test)
        test_tv.maxWidth=400
        val span=SpannableStringBuilder(SpannableString("women dress"))
        span.append(" · 3M Products")
        val foregroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF0000"))
        span.setSpan(foregroundColorSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        test_tv.text = span
    }
}