package com.example.studyapplication.view

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationLinearLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int = -1
) : LinearLayout(context, attrs, def) {
    private var objectAnimatorX: ObjectAnimator? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.activity_animation_ll, null)
    }

    fun startPopsAnimTrans() {

        if (objectAnimatorX == null) {
            objectAnimatorX = ObjectAnimator.ofFloat(this, "translationY", 0f, (-height).toFloat())
            objectAnimatorX!!.duration = 200
        }
        objectAnimatorX!!.start()
        val evaluator = FloatEvaluator()
        objectAnimatorX!!.addUpdateListener {
            val fraction = it.animatedFraction
            layoutParams.apply {
                height = evaluator.evaluate(fraction, 0f, (-height).toFloat()).toInt()
            }
            requestLayout()
        }
    }

    fun addMyView() {
        addView((LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            gravity = Gravity.CENTER_HORIZONTAL
        }))
        for (i in 0..3) {
            (getChildAt(0) as LinearLayout).addView(TextView(context).apply {
                layoutParams = LinearLayout.LayoutParams(100, 100)
                text="2222222"
                setBackgroundColor(Color.parseColor("#4683e7"))
            })
        }
    }

    class FloatEvaluator : TypeEvaluator<Float> {
        override fun evaluate(fraction: Float, startValue: Float, endValue: Float): Float {
            return (startValue + fraction * (endValue - startValue)) * -1
        }
    }
}