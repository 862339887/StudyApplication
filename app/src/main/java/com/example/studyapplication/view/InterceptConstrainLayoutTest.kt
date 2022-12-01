package com.example.studyapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout

class InterceptConstrainLayoutTest @JvmOverloads constructor(
    ctx: Context? = null,
    attrs: AttributeSet? = null,
    def: Int = -1
) : ConstraintLayout(ctx!!, attrs, def) {
//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        return true
//    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        return true
        return super.dispatchTouchEvent(ev)
    }




//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return false
//    }
//
//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
////        return super.onInterceptTouchEvent(ev)
//        return true
//    }
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return true
//    }

}