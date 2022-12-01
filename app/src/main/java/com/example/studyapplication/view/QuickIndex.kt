package com.example.studyapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.MotionEventCompat
import com.example.studyapplication.R


class QuickIndexBar(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
    //画笔
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //每个字母所在的矩形的宽高
    private var cellWidth = 0f
    private var cellHeight = 0f

    /*
       * 设置字母监听更新
       * @param listener
       */
    //回调接口
    var onLetterUpdateListener: OnLetterUpdateListener? = null

    /*
       * 暴露一个字母的监听
       */
    interface OnLetterUpdateListener {
        fun letterUpdate(letter: String?)
    }

    constructor(context: Context?) : this(context, null) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {}

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        for (i in LETTERS.indices) {
            val text = LETTERS[i]

            // 计算坐标
            val x = (cellWidth / 2.0f - mPaint.measureText(text) / 2.0f).toInt()
            // 获取文本高度
            val bounds = Rect()
            //设置字体大小为cellHeight的三分之二大小
            mPaint.textSize =
                context.resources.getDimensionPixelOffset(R.dimen.mtrl_badge_text_size).toFloat()
            mPaint.getTextBounds(text, 0, text.length, bounds)
            val textHeight: Int = bounds.height()
            val y = (cellHeight / 2.0f + textHeight / 2.0f + (i
                    * cellHeight)).toInt()

            // 根据按下的字母, 设置画笔颜色
            mPaint.color = if (touchIndex == i) ContextCompat.getColor(
                context,
                R.color.app_title_color
            ) else ContextCompat.getColor(context, R.color.blue_dark)
            canvas.drawText(text, x.toFloat(), y.toFloat(), mPaint)
        }
    }

    var touchIndex = -1

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var index = -1
        when (MotionEventCompat.getActionMasked(event)) {
            MotionEvent.ACTION_DOWN -> {
                index = (event.y / cellHeight).toInt()
                if (index >= 0 && index < LETTERS.size) {
                    if (index != touchIndex) {
                        onLetterUpdateListener!!.letterUpdate(LETTERS[index])
                    }
                    touchIndex = index
                }
            }
            MotionEvent.ACTION_UP -> {
                index = -1
                touchIndex = -1
            }
            MotionEvent.ACTION_MOVE -> {
                index = (event.y / cellHeight).toInt()
                if (index >= 0 && index < LETTERS.size) {
                    if (index != touchIndex) {
                        onLetterUpdateListener!!.letterUpdate(LETTERS[index])
                    }
                    touchIndex = index
                }
            }
            else -> {
            }
        }
        invalidate()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cellWidth = measuredWidth.toFloat()
        val mHeight: Int = measuredHeight
        cellHeight = mHeight * 1.0f / LETTERS.size
    }

    companion object {
        private val LETTERS = arrayOf(
            "a", "b", "c", "d",
            "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
            "r", "s", "t", "u", "v", "w", "x", "y", "z"
        )
    }

    init {
        mPaint.color = -0x777778
        mPaint.typeface = Typeface.DEFAULT_BOLD
    }
}