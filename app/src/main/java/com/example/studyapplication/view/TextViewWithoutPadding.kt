package com.example.studyapplication.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent

class TextViewWithoutPadding @JvmOverloads constructor(
    ctx: Context? = null,
    attrs: AttributeSet? = null,
    def: Int = -1
) : androidx.appcompat.widget.AppCompatTextView(ctx!!, attrs, def) {

    //画矩形
    private val rectPaint: Paint = Paint()
    private val rectPaint1: Paint = Paint()

    private val textPaint = Paint()

    private var textRectF = RectF()
    private var upRectF = RectF()
    private var downRectF = RectF()

    private var array = floatArrayOf(-1f, -1f)

    init {
        textRectF = RectF(0f, 0f, (56 * 2.75).toFloat(), (56 * 2.75).toFloat())
        downRectF = RectF(
            (7 * 2.75).toFloat(),
            (24 * 2.75).toFloat(),
            (49 * 2.75).toFloat(),
            (109 * 2.75).toFloat()
        )

        rectPaint.apply {
            isAntiAlias = true
            val linearGradient = LinearGradient(
                0f,
                0f,
                0f,
                108 * 2.75.toFloat(),
                Color.parseColor("#F2F4F6"),
                Color.parseColor("#D8DFE5"),
                Shader.TileMode.CLAMP
            )
            shader = linearGradient
            style = Paint.Style.FILL
        }
        rectPaint1.apply {
            isAntiAlias = true
            val linearGradient = LinearGradient(
                0f,
                108 * 2.75.toFloat(),
                0f,
                0f,
                Color.parseColor("#D8DFE5"),
                Color.parseColor("#F2F4F6"),
                Shader.TileMode.CLAMP
            )
            shader = linearGradient
            style = Paint.Style.FILL
        }
        textPaint.apply {
            isAntiAlias = true
            textSize = 23 * 2.75f
            color = Color.parseColor("#7A8B9D")
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension((56 * 2.75).toInt(), (108 * 2.75).toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val bitmap: Bitmap
        canvas?.drawRoundRect(downRectF, (30 * 2.75).toFloat(), (30 * 2.75).toFloat(), rectPaint1)
        canvas?.drawRoundRect(textRectF, (11 * 2.75).toFloat(), (11 * 2.75).toFloat(), rectPaint)
//        val fontMetrics = textPaint.fontMetrics
//        val top = fontMetrics.top
//        val bottom = fontMetrics.bottom
//        val baseLineY = (textRectF.centerY() - top / 2 - bottom / 2).toInt() //基线中间点的y轴计算公式
//        canvas!!.drawText(text as String, textRectF.centerX(), baseLineY.toFloat(), textPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                array[0] = event.rawX
                array[1] = event.rawX
                print("a")
            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return super.onTouchEvent(event)
    }
}