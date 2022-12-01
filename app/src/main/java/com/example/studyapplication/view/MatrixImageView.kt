package com.example.studyapplication.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.studyapplication.R

class MatrixImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int = -1
) :
    AppCompatImageView(
        context, attrs, def
    ) {

    private var flag = false
    private var paint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = context.resources.getColor(R.color.blue_dark8)
        strokeWidth = 10f
    }

    fun setTranslateAction(flag: Boolean) {
        this.flag = flag
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (flag) {
            doTranslate(canvas)
        }
    }

    private fun doTranslate(canvas: Canvas?) {
        val matrix = Matrix()
        val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.wallpaper)
        matrix.setTranslate(360f, 935f)
        canvas?.drawBitmap(bitmap, matrix, paint)
    }
}