package com.example.studyapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.example.studyapplication.R


class MaskImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int = -1
) :
    AppCompatImageView(
        context, attrs, def
    ) {

    private var flag = false
    private var maskFlag = false

    private var drawBitmap: Bitmap? = null

    fun setDrawBitmap(drawBitmap: Bitmap) {
        this.drawBitmap = drawBitmap
    }

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private var paint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.parseColor("#991F2329")
//        color = context.resources.getColor(R.color.blue_dark9)
        strokeWidth = 10f
    }

    fun setTranslateAction(flag: Boolean) {
        this.flag = flag
        postInvalidate()
    }

    fun setMask(maskFlag: Boolean) {
        this.maskFlag = maskFlag
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        if (flag) {
//            doTranslate(canvas)
//        }
        if (maskFlag) {
            val layerId = canvas?.saveLayer(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                null,
                Canvas.ALL_SAVE_FLAG
            )
            val rect1 = RectF(
                0f,
                (height - drawBitmap!!.height) / 2f,
                drawBitmap!!.width.toFloat(),
                (height - drawBitmap!!.height) / 2f + drawBitmap!!.height.toFloat()
            )
            canvas?.drawRect(rect1, paint)

            val rect2 = RectF(
                300f,
                (height - drawBitmap!!.height) / 2f + 300f,
                600f,
                (height - drawBitmap!!.height) / 2f + 600f
            )
            paint.color
            paint.style = Paint.Style.FILL_AND_STROKE
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            canvas?.drawRect(rect2, paint)
            paint.xfermode = null
            if (layerId != null) {
                canvas.restoreToCount(layerId)
            };
        }
    }


    private fun doTranslate(canvas: Canvas?) {
        val matrix = Matrix()
        val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.wallpaper)
        matrix.setTranslate(360f, 935f)
        canvas?.drawBitmap(bitmap, matrix, paint)
    }
}