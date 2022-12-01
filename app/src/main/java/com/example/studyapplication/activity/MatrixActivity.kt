package com.example.studyapplication.activity

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_matrix.*
import java.io.File
import java.io.FileOutputStream
import java.util.Collections.min

class MatrixActivity : AppCompatActivity() {
    private val matrixValues = FloatArray(9)
    private var originMatrix: Matrix? = null
    private var times = 1
    private var rect: RectF? = null
    private var pointTopLeft: PointF? = null
    private var rectMatrix: Matrix? = null
//    private lateinit var originBitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)
//        originBitmap = (image_show_iv.drawable as BitmapDrawable).bitmap
        print("a")

        initView()
        rectMatrix = Matrix()
//        image_show_iv.postDelayed({
//            val matrix = image_show_iv.imageMatrix
//            val xScaleCurrent = getValue(matrix, Matrix.MSCALE_X)
//            val yScaleCurrent = getValue(matrix, Matrix.MSCALE_Y)
//            val xTransCurrent = getValue(matrix, Matrix.MTRANS_X)
//            val yTransCurrent = getValue(matrix, Matrix.MTRANS_Y)
//            pointTopLeft = PointF(xTransCurrent + originBitmap.width * xScaleCurrent, yTransCurrent)
//            rect = RectF(0f, 0f, 300f, 200f)
//            val rectMatrix = image_show_iv.imageMatrix
//            rectMatrix.mapRect(rect)
//            image_show_iv.doDrawRect(true, rect)
//            pointTopLeft = PointF(rect!!.right, rect!!.top)
            originMatrix = image_show_iv.imageMatrix
//        }, 200)
    }

    private fun initView() {
        matrix_button_save.setOnClickListener {
//            val left = image_show_iv.attacher.mRenderRect.left
//            val top = image_show_iv.attacher.mRenderRect.top
//            val imageMatrix = image_show_iv.imageMatrix
//            val newBitmap = Bitmap.createBitmap(
//                originBitmap,
//                0,
//                0,
//                originBitmap.width,
//                originBitmap.height,
//                imageMatrix,
//                true
//            )
//            val clippedBitmap = Bitmap.createBitmap(
//                newBitmap,
//                rect!!.left.toInt() - left.toInt(),
//                rect!!.top.toInt() - top.toInt(),
//                rect!!.width().toInt(),
//                rect!!.height().toInt()
//            )
//            print("a")
        }
        matrix_button.setOnClickListener {
//            val imageMatrix = image_show_iv.imageMatrix
//            val newBitmap = Bitmap.createBitmap(
//                originBitmap,
//                0,
//                0,
//                originBitmap.width,
//                originBitmap.height,
//                imageMatrix,
//                true
//            )
//            image_show_iv.setRotationBy(-90f)
//
//            val matrix = Matrix()
//            matrix.postRotate(-90f, image_show_iv.width / 2f, image_show_iv.height / 2f)
////            matrix.postTranslate(0f, -300f)
//            matrix.postConcat(image_show_iv.attacher.checkMatrix)
////            image_show_iv.imageMatrix = mDrawMatrix
//            matrix.mapRect(rect)
//            val floatArray = floatArrayOf(pointTopLeft!!.x, pointTopLeft!!.y)
//            matrix.mapPoints(floatArray)
//            pointTopLeft = PointF(floatArray[0], floatArray[1])
//            image_show_iv.doDrawRect(true, rect)
//            println("a")
        }
    }

    /**
     * 获取指定matrix中的信息，如缩放比例、偏移量等
     */
    private fun getValue(matrix: Matrix, whichValue: Int): Float {
        matrix.getValues(matrixValues)
        return matrixValues[whichValue]
    }

}