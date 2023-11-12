package com.example.studyapplication.others

import android.graphics.Camera
import android.graphics.Matrix
import android.view.animation.Animation
import android.view.animation.Transformation


/**
 * Created by zhouxin on 2023/1/13
 * @author zhouxin.3012143@bytedance.com
 */
class Rotate3dAnimation(
    private val mFromDegrees: Float,
    private val mToDegrees: Float,
    private val mCenterX: Float,
    private val mCenterY: Float,
    private val mDepthZ: Float,
    private val mReverse: Boolean
) : Animation() {
    private var mCamera: Camera? = null
    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mCamera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val fromDegrees = mFromDegrees
        val degrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime
        val centerX = mCenterX
        val centerY = mCenterY
        val camera: Camera? = mCamera
        val matrix: Matrix = t.matrix
        //保存一次camera初始状态，用于restore()
        camera?.save()
        if (mReverse) {
            camera?.translate(0.0f, 0.0f, mDepthZ * interpolatedTime)
        } else {
            camera?.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime))
        }
        //围绕Y轴旋转degrees度
        camera?.rotateX(degrees)
        //行camera中取出矩阵，赋值给matrix
        camera?.getMatrix(matrix)
        //camera恢复到初始状态，继续用于下次的计算
        camera?.restore()
        matrix.preTranslate(-centerX, -centerY)
        matrix.postTranslate(centerX, centerY)
    }
}