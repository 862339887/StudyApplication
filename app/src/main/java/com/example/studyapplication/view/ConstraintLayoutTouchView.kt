package com.example.studyapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.abs

class ConstraintLayoutTouchView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    def: Int = -1
) :
    ConstraintLayout(ctx, attrs, def) {
    private var rootTouchListener: RootTouchListener? = null
    private var currentX = -1f
    private var currentY = -1f
    private var preX = -1f
    private var preY = -1f
    private var offset = 2
    private var mSrcCanvas: Canvas? = null//绘制Canvas
    private var mPaint: Paint? = null//画笔paint
    private var mPath: Path? = null
    private var flag = false
    private var isActionDown = true

    init {
        mPath = Path()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = 5f
        mPaint!!.color = Color.RED

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        currentX = event!!.x
        currentY = event.y
        Log.e("fdfafdatet555555", "onTouchEvent:$currentX,$currentY ")

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                flag = true
                preX = currentX
                preY = currentY
                mPath!!.moveTo(currentX, currentY)
                isActionDown = false
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = abs(currentX - preX)
                val dy = abs(currentY - preY)
                if (dx >= offset || dy >= offset) {
                    // 贝塞尔曲线的控制点为起点和终点的中点
                    val cX = (currentX + preX) / 2
                    val cY = (currentY + preY) / 2
                    mPath!!.quadTo(preX, preY, cX, cY)
                }
                invalidate()
                return true
            }
        }
        return false
    }

    fun setCallBack(rootTouchListener: RootTouchListener) {
        this.rootTouchListener = rootTouchListener
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mSrcCanvas = canvas
        mSrcCanvas!!.drawPath(mPath!!, mPaint!!)
        Log.e("fdfafdateaaa111", "onTouchEvent:$currentX,$currentY ")
        if (flag) {
            if (isActionDown) {
                Log.e("fdfafdateaaa111", "onTouchEvent:$currentX,$currentY ")
                rootTouchListener!!.drawPath(currentX, currentY)
            } else {
                Log.e("fdfafdateaaa2222", "onTouchEvent:$currentX,$currentY ")
                rootTouchListener!!.drawPath(currentX, currentY)

            }
            preX = currentX
            preY = currentY
        }
    }

    fun clear() {
        mPaint = Paint()
        mPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        mSrcCanvas?.drawPaint(mPaint!!)
        mPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC)
        invalidate()
    }
}


interface RootTouchListener {
    fun drawPath(currentX: Float, currentY: Float)
}