package com.example.studyapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CanvasDrawView @JvmOverloads constructor(
    ctx: Context? = null,
    attrs: AttributeSet? = null,
    def: Int = -1
) :
    View(ctx, attrs, def), RootTouchListener {

    private var dotPathList = ArrayList<DotPath>()

    //笔迹点数据
    private var leftTopPos: DotPath? = null
    private var rightTopPos: DotPath? = null
    private var rightBottomPos: DotPath? = null
    private var leftBottomPos: DotPath? = null

    private var leftPos: DotPath? = null
    private var topPos: DotPath? = null
    private var rightPos: DotPath? = null
    private var bottomPos: DotPath? = null

    private var currentWidth: Int = 0
    private var currentHeight: Int = 0
    private var challenge: DotPath? = null
    private var mSrcCanvas: Canvas? = null//绘制Canvas
    private var mPaint: Paint? = null//画笔paint
    private var mPath: Path? = null
    private var offset = 2

    init {
        mPath = Path()
        mPaint = Paint()
        mPaint!!.apply {
            isAntiAlias = true
            strokeWidth = 1.2f
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            strokeMiter = 0.3f
            isFilterBitmap = true
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mSrcCanvas = canvas ?: return
        mSrcCanvas!!.drawPath(mPath ?: return, mPaint ?: return)
    }


    fun drawPath1(dotPathList: List<DotPath>) {
        mPath!!.reset()
        var preX = -1f
        var preY = -1f
        dotPathList.forEach {
            if (preX == -1f || preY == -1f) {
                preX = it.currentX
                preY = it.currentY
                mPath!!.moveTo(it.currentX,it.currentY)
            } else {
                val dx = abs(it.currentX - preX)
                val dy = abs(it.currentY - preY)
                if (dx >= offset || dy >= offset) {
                    // 贝塞尔曲线的控制点为起点和终点的中点
                    val cX = (it.currentX + preX) / 2
                    val cY = (it.currentY + preY) / 2
                    mPath!!.quadTo(preX, preY, cX, cY)
                }
                invalidate()
                preX = it.currentX
                preY = it.currentY
            }
        }
    }

//    private fun traceTarget(challenge: DotPath) {
//        Log.e("fdsfafaf", "traceTarget: ${challenge.currentX},${challenge.currentY}")
//        if (leftTopPos == null) {
//            this.challenge = challenge
//            leftTopPos = challenge
//            rightTopPos = challenge
//            rightBottomPos = challenge
//            leftBottomPos = challenge
//            leftPos = challenge
//            topPos = challenge
//            rightPos = challenge
//            bottomPos = challenge
//            dotPathList.add(DotPath(0f, 0f))
//            reDraw(dotPathList)
//            return
//        }
//        this.challenge = challenge
//        if (challenge.currentX < leftTopPos!!.currentX && challenge.currentY < leftTopPos!!.currentY) {
//            //更新左上角
//            leftTopPos = challenge
//            val offsetX = leftTopPos!!.currentX - challenge.currentX
//            val offsetY = leftTopPos!!.currentY - challenge.currentY
//            dotPathList.forEach {
//                it.currentX += offsetX
//                it.currentY += offsetY
//            }
//            dotPathList.add(DotPath(0f, 0f))
//        } else if (challenge.currentX > rightTopPos!!.currentX && challenge.currentY < rightTopPos!!.currentY) {
//            //更新右上角
//            Log.e("leee-test", "2")
//            rightTopPos = challenge
//            val offsetY = rightTopPos!!.currentY - challenge.currentY
//            dotPathList.forEach {
//                it.currentY += offsetY
//            }
//            dotPathList.add(DotPath(challenge.currentX - leftTopPos!!.currentX, 0f))
//        } else if (challenge.currentX > rightBottomPos!!.currentX && challenge.currentY > rightBottomPos!!.currentY) {
//            //更新右下角
//            Log.e("leee-test", "3")
//            rightBottomPos = challenge
//            dotPathList.add(
//                DotPath(
//                    challenge.currentX - leftTopPos!!.currentX,
//                    challenge.currentY - leftTopPos!!.currentY
//                )
//            )
//        } else if (challenge.currentX < leftBottomPos!!.currentX && challenge.currentY > leftBottomPos!!.currentY) {
//            //更新左下角
//            Log.e("leee-test", "4")
//            leftBottomPos = challenge
//            val offsetX = leftBottomPos!!.currentX - challenge.currentX
//            dotPathList.forEach {
//                it.currentX += offsetX
//            }
//            dotPathList.add(DotPath(0f, challenge.currentY - leftTopPos!!.currentY))
//        } else if (challenge.currentX < leftPos!!.currentX && challenge.currentY > leftTopPos!!.currentY && challenge.currentY < leftBottomPos!!.currentY) {
//            //更新左边
//            Log.e("leee-test", "5")
//            leftPos = challenge
//            val offsetX = leftBottomPos!!.currentX - challenge.currentX
//            dotPathList.forEach {
//                it.currentX += offsetX
//            }
//            dotPathList.add(DotPath(0f, challenge.currentY - leftTopPos!!.currentY))
//        } else if (challenge.currentY < topPos!!.currentY && challenge.currentX > leftTopPos!!.currentX && challenge.currentX < rightTopPos!!.currentX) {
//            //更新上边
//            Log.e("leee-test", "6")
//            topPos = challenge
//            val offsetY = topPos!!.currentY - challenge.currentY
//            dotPathList.forEach {
//                it.currentY += offsetY
//            }
//            dotPathList.add(DotPath(challenge.currentX - leftTopPos!!.currentX, 0f))
//        } else if (challenge.currentX > rightPos!!.currentX && challenge.currentY > rightTopPos!!.currentY && challenge.currentY < rightBottomPos!!.currentY) {
//            //更新右边
//            Log.e("leee-test", "7")
//            rightPos = challenge
//            dotPathList.add(
//                DotPath(
//                    challenge.currentX - leftTopPos!!.currentX,
//                    challenge.currentY - leftTopPos!!.currentY
//                )
//            )
//        } else if (challenge.currentY > bottomPos!!.currentY && challenge.currentX > leftBottomPos!!.currentX && challenge.currentX < rightBottomPos!!.currentX) {
//            //更新下边
//            Log.e("leee-test", "8")
//            bottomPos = challenge
//            dotPathList.add(
//                DotPath(
//                    challenge.currentX - leftTopPos!!.currentX,
//                    challenge.currentY - leftTopPos!!.currentY
//                )
//            )
//        } else {
//            dotPathList.add(
//                DotPath(
//                    challenge.currentX - leftTopPos!!.currentX,
//                    challenge.currentY - leftTopPos!!.currentY
//                )
//            )
//        }
//        calculateSize()
//        reDraw(dotPathList)
//    }

//    private fun calculateSize() {
//        val left = min(min(leftTopPos!!.currentX, leftBottomPos!!.currentX), leftPos!!.currentX)
//        val right = max(max(rightTopPos!!.currentX, rightBottomPos!!.currentX), rightPos!!.currentX)
//        currentWidth = (right - left).toInt()
//        val top = min(min(leftTopPos!!.currentY, rightTopPos!!.currentY), topPos!!.currentY)
//        val bottom =
//            max(max(leftBottomPos!!.currentY, rightBottomPos!!.currentY), bottomPos!!.currentY)
//        currentHeight = (bottom - top).toInt()
//        Log.e("leee-test", "9--${currentWidth}---${currentHeight}")
//        refreshLayout()
//    }
//
//    private fun refreshLayout() {
//        val layoutParams = this.layoutParams
//        layoutParams.width = currentWidth
//        layoutParams.height = currentHeight
//        setLayoutParams(layoutParams)
//    }


//    fun reDraw(dotPathList: List<DotPath>) {
//        mPath!!.reset()
//        var preX = -1f
//        var preY = -1f
//        dotPathList.forEach {
//            if (preX == -1f || preY == -1f) {
//                preX = it.currentX
//                preY = it.currentY
//            } else {
//                val dx = abs(it.currentX - preX)
//                val dy = abs(it.currentY - preY)
//                if (dx >= offset || dy >= offset) {
//                    // 贝塞尔曲线的控制点为起点和终点的中点
//                    val cX = (it.currentX + preX) / 2
//                    val cY = (it.currentY + preY) / 2
//                    mPath!!.quadTo(preX, preY, cX, cY)
//                }
//                invalidate()
//            }
//        }
//    }

    override fun drawPath(currentX: Float, currentY: Float) {
        traceTarget1(DotPath(currentX, currentY))
    }

    private fun traceTarget1(challenge: DotPath) {
        dotPathList.add(challenge)
        drawPath1(dotPathList)
    }

}

data class DotPath(var currentX: Float, var currentY: Float)

