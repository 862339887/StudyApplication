package com.example.studyapplication.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView

class TestImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int = -1
) : AppCompatImageView(context, attrs, def) {
    private var times = 0
    private var paint: Paint? = null
    private lateinit var currentRect: Rect
    private var flag = false
    private var clearFlag = false


//    private fun getClipBitmap(): Bitmap? {
//        val left: Int = currentRect.left
//        val top: Int = currentRect.top
//        val right: Int = currentRect.right
//        val bottom: Int = currentRect.bottom
//        return Bitmap.createBitmap(inputCondition.rawBitmap, left, top, right - left, bottom - top)
//    }

    fun doPostDraw() {
        paint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            color = Color.RED
            strokeWidth = 10F
        }
        currentRect = Rect(0, 0, 100, 400)
        postInvalidate()
    }

    fun doRotate(flag: Boolean) {
        this.flag = flag
        postInvalidate()
    }

//    fun clearCanvas(flag: Boolean) {
//        this.clearFlag = flag
//        postInvalidate()
//    }

    private fun rotateRect(canvas: Canvas?) {
//        rect = Rect(0, 0, 100, 100)
//        canvas?.drawRect(rect, paint!!)
        canvas?.save();
        val x = (currentRect.right - currentRect.left) / 2
        val y = (currentRect.bottom - currentRect.top) / 2
        canvas?.rotate(45f , x.toFloat(), y.toFloat());
        canvas?.drawRect(currentRect, paint!!);
        canvas?.restore();
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("zhouxin--onDraw", "onDraw: ")
//        if (clearFlag) {
//            canvas?.drawColor(0, PorterDuff.Mode.CLEAR);
//        } else {
        if (flag) {
            rotateRect(canvas)
        } else {
            if (paint != null) {
                canvas?.drawRect(currentRect, paint!!)
            }
        }
//        }

    }
}