package com.example.studyapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout

class InterceptConstrainLayout @JvmOverloads constructor(
    ctx: Context? = null,
    attrs: AttributeSet? = null,
    def: Int = -1
) : ConstraintLayout(ctx!!, attrs, def) {
    private var isIntercept = false
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (isIntercept) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    fun setInterceptValue(isIntercept: Boolean) {
        this.isIntercept = isIntercept
    }
}