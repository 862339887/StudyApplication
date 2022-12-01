package com.example.studyapplication.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.studyapplication.R

class BitmapTestImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int = -1
) :
    AppCompatImageView(
        context, attrs, def
    ) {
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}