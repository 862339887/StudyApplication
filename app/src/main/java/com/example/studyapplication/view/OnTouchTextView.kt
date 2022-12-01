package com.example.studyapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class OnTouchTextView @JvmOverloads constructor(
    ctx: Context? = null,
    attrs: AttributeSet? = null,
    def: Int = -1
) : androidx.appcompat.widget.AppCompatTextView(ctx!!, attrs, def){

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return false
    }
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return false
//    }
}