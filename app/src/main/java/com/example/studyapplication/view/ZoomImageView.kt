package com.example.studyapplication.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlin.math.abs
import kotlin.math.sqrt


/**
 * @author lihanguang
 */
class ZoomImageView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    def: Int = -1
) : androidx.appcompat.widget.AppCompatImageView(ctx, attrs, def) {
    /**
     * 父ViewGroup
     */
    private var parent: ViewGroup? = null

    /**
     * thisImageView
     */
    private var thisImageView: ImageView? = null

    /**
     * 上一次时的matrix
     */
    private var mSaveMatrix: Matrix? = null

    /**
     * 当前的matrix
     */
    private var mCurrentMatrix: Matrix? = null

    /**
     * 辅助触摸事件
     */
    private var mGestureDetector: GestureDetector? = null

    /**
     * 初始化时的matrix
     */
    private var mInitMatrix: Matrix? = null

    /**
     * 矩阵的值
     */
    private val values = FloatArray(9)

    /**
     * 操作的模式
     */
    private var mode = NONE

    /**
     * 大小模式
     */
    private var sizeMode = NONE

    /**
     * 图片的宽度
     */
    private var mImageWidth = 0f

    /**
     * 图片的高度
     */
    private var mImageHeight = 0f

    /**
     * view的宽度
     */
    private var mViewWidth = 0f

    /**
     * view的高度
     */
    private var mViewHeight = 0f

    /**
     * 起始点位置
     */
    private var startPoint: PointF? = null

    /**
     * 上一次移动的点
     */
    private var lastPoint: PointF? = null

    /**
     * 缩放时的缩放点
     */
    private var midPoint: PointF? = null

    /**
     * 上一次移动的x轴的量
     */
    private var lastTranX = 0f

    /**
     * 上一次移动的y轴的量
     */
    private var lastTranY = 0f

    /**
     * 判断是否调用过onDraw方法
     */
    private var isOnDraw = false

    /**
     * 缩放前两点的距离
     */
    private var oldDistance = 0f

    /**
     * 用于完成拖拽或缩放后的动画效果
     */
    private var animator: ValueAnimator? = null

    /**
     * 在动画中需要平移的x量
     */
    private var tranX = 0f

    /**
     * 在动画中需要平移的y量
     */
    private var tranY = 0f

    /**
     * 在动画中需要缩放的倍数
     */
    private var scale = 1f

    /**
     * 是否在动画中
     */
    private var isAnimating = false

    /**
     * 是否在缩小中，缩小时无法中断
     */
    private var isNarrowing = false

    /**
     * 单击时的回调
     */
    private var mOnSingleClick: OnSingleClick? = null

    /**
     * 已经拖拽到最右边
     */
    private var inRight = false

    /**
     * 已经拖拽到最左边
     */
    private var inLeft = false

    /**
     * 是否可水平滑动
     */
    private var ableTranX = false

    /**
     * 是否可竖直滑动
     */
    private var ableTranY = false

    /**
     * 是否拦截左滑动
     */
    private var isInterruptLeft = true

    /**
     * 是否拦截右滑动
     */
    private var isInterruptRight = true

    /**
     * 是否判断屏蔽滑动事件
     */
    private var isJudgeInterrputTouch = false

    /**
     * 是否放大或还原后
     */
    private var isEnlargeOrRestore = false

    /**
     * 是否在after方法后
     */
    private var isAfter = false

    /**
     * 弹性滑动的X速度
     */
    private var scrollSpeedX = 0f

    /**
     * 弹性滑动的Y速度
     */
    private var scrollSpeedY = 0f

    /**
     * 是否已加载完毕
     */
    private var isLoaded = false


    init {
        init()
    }

    /**
     * 初始化操作
     */
    private fun init() {
        val mListener = ZoomListener()
        initSimpleOnGestureListener()
        mGestureDetector = GestureDetector(context, simpleOnGestureListener)
        setOnTouchListener(mListener)
        mCurrentMatrix = Matrix()
        mSaveMatrix = Matrix()
        mInitMatrix = Matrix()
        midPoint = PointF()
        startPoint = PointF()
        lastPoint = PointF()
        thisImageView = this
        parent = if (getParent() is ViewGroup) getParent() as ViewGroup else null
        super.setScaleType(ScaleType.MATRIX)
    }

    override fun setScaleType(scaleType: ScaleType) {
        super.setScaleType(ScaleType.MATRIX)
    }

    /**
     * 初始化图片位置
     */
    private fun initImage() {
        // 将图片缩放到宽度与view的宽度相同，平移到view的中心
        val width = width.toFloat()
        val height = height.toFloat()
        val values = floatArrayOf(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
        mCurrentMatrix!!.setValues(values)
        getWidthAndHeight(mCurrentMatrix)
        // 没有获得宽高的时候不进行初始化
        if (mImageHeight == 0f || mImageWidth == 0f || mViewHeight == 0f || mViewWidth == 0f) {
            return
        }
        // 初始化时宽和控件的宽相同
        val scaleX = width / mImageWidth
        mCurrentMatrix!!.postScale(scaleX, scaleX)
        getWidthAndHeight(mCurrentMatrix)
        if (mImageHeight <= mViewHeight) { // 只有在缩放后图片的高度小于view的高度才需要向下平移
            // 平移,因为宽已经和view的宽一样，所以只需要向下平移
            val tranY = (height - mImageHeight) / 2
            mCurrentMatrix!!.postTranslate(0f, tranY)
            mode = NONE
        } else {
            mode = DRAG
            ableTranY = true
        }
        imageMatrix = mCurrentMatrix
        mInitMatrix!!.set(mCurrentMatrix)
        isLoaded = true
    }

    override fun onDraw(canvas: Canvas) {
        if (!isOnDraw) {
            init()
            initImage()
            isOnDraw = true
        }
        super.onDraw(canvas)
    }

    /**
     * 重写setImageBitmap方法，获取图片的matrix
     */
    override fun setImageBitmap(bm: Bitmap) {
        super.setImageBitmap(bm)
        isLoaded = false
        init()
        initImage()
    }

    /**
     * 获取图片的宽高
     */
    private fun getWidthAndHeight(matrix: Matrix?) {
        val bd = drawable as BitmapDrawable
        val bm = bd.bitmap ?: return
        val values = FloatArray(9)
        matrix!!.getValues(values)
        // 获取图片的宽和高，
        mImageWidth = bm.width * values[Matrix.MSCALE_X]
        mImageHeight = bm.height * values[Matrix.MSCALE_X]
        // 获取view的宽高
        mViewWidth = width.toFloat()
        mViewHeight = height.toFloat()

        // 获得拖动参数
        ableTranX = mImageWidth > mViewWidth
        ableTranY = mImageHeight > mViewHeight
    }

    /**
     * 设置父viewGroup
     *
     * @param viewGroup
     */
    fun setParent(viewGroup: ViewGroup?) {
        parent = viewGroup
    }

    /**
     * 滑动，快速拖拽后放手发动
     *
     * @return
     */
    private fun scroll(xVelocity: Float, yVelocity: Float): Boolean {
        if (!isLoaded) {
            return false
        }
        if (Math.abs(xVelocity) >= MIN_SCROLL_SPEED || Math.abs(yVelocity) >= MIN_SCROLL_SPEED) {
            if (Math.abs(xVelocity) > Math.abs(yVelocity)) {
                if (Math.abs(xVelocity) >= MIN_SCROLL_SPEED) {
                    scrollSpeedX = xVelocity
                    scrollSpeedY = 0f
                    tranX = 0f
                    tranY = 0f
                }
            } else {
                if (Math.abs(yVelocity) >= MIN_SCROLL_SPEED) {
                    scrollSpeedX = 0f
                    scrollSpeedY = yVelocity
                    tranY = 0f
                    tranX = 0f
                }
            }
            if (animator != null && animator!!.isRunning) {
                animator!!.cancel()
            }
            mSaveMatrix!!.set(mCurrentMatrix)
            getWidthAndHeight(mCurrentMatrix)
            animator = ValueAnimator.ofFloat(1f, 0f)
            animator!!.setDuration(1000)
            animator!!.addUpdateListener(mScrollAnimatorListener)
            animator!!.addListener(listener)
            animator!!.start()
            isAnimating = true
            return true
        }
        return false
    }

    /**
     * 滑动动画结束
     */
    var listener: Animator.AnimatorListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {}
        override fun onAnimationRepeat(animation: Animator) {}
        override fun onAnimationEnd(animation: Animator) {
            isAnimating = false
            tranX = 0f
            tranY = 0f
            scale = 1f
            after() // 结束时
        }

        override fun onAnimationCancel(animation: Animator) {}
    }

    /**
     * 滑动时的动画器
     */
    private val mScrollAnimatorListener =
        AnimatorUpdateListener { animation ->
            val value = animation.animatedValue as Float
            val nowSpeedX = value * scrollSpeedX
            val nowSpeedY = value * scrollSpeedY
            tranX += nowSpeedX * 0.01f
            tranY += nowSpeedY * 0.01f
            Log.d("fiend", "tranX:$tranX")
            if (!ableTranX) {
                tranX = 0f
            }
            if (!ableTranY) {
                tranY = 0f
            }
            mCurrentMatrix!!.set(mSaveMatrix)
            mCurrentMatrix!!.postTranslate(tranX, tranY)
            imageMatrix = mCurrentMatrix
            getWidthAndHeight(mCurrentMatrix)
            mCurrentMatrix!!.getValues(values)
            val mTranX = values[Matrix.MTRANS_X]
            val mTranY = values[Matrix.MTRANS_Y]
            if (scrollSpeedX != 0f) {
                if (mTranX > 0 || Math.abs(mTranX) > mImageWidth - mViewWidth) {
                    Log.d("fiend", "tranX:$tranX")
                    Log.d("fiend", "结束")
                    if (animation.isRunning) {
                        animation.cancel()
                        after() // 结束时
                    }
                }
            }
            if (scrollSpeedY != 0f) {
                if (mTranY > 0 || Math.abs(mTranY) > mImageHeight - mViewHeight) {
                    if (animation.isRunning) {
                        animation.cancel()
                        after() // 结束时
                    }
                }
            }
        }

    /**
     * 还原到未缩放和拖拽的大小
     */
    fun restore() {
        Log.d(TAG, "restore")
        if (!isLoaded) {
            return
        }
        if (sizeMode == ENLARGE) { // 只有在放大的情况下才能还原
            mCurrentMatrix!!.getValues(values)
            getWidthAndHeight(mCurrentMatrix)
            isEnlargeOrRestore = true
            // 当前的偏移和缩放倍数
            val mCurrentTranX = values[Matrix.MTRANS_X]
            val mCurrentTranY = values[Matrix.MTRANS_Y]
            val mCurrentScale = values[Matrix.MSCALE_X]
            mInitMatrix!!.getValues(values)
            // 目标偏移和缩放
            val mTargetTranX = values[Matrix.MTRANS_X]
            val mTargetTranY = values[Matrix.MTRANS_Y]
            val mTargetScale = values[Matrix.MSCALE_X]
            scale = mTargetScale / mCurrentScale
            tranX = mTargetTranX - mCurrentTranX
            tranY = mTargetTranY - mCurrentTranY
            // startAnimator();
            startAnimator(tranX, tranY, scale, null)
        }
    }

    /**
     * 放大到2倍
     */
    private fun enlarge() {
        if (!isLoaded) {
            return
        }
        if (mode == NONE) {
            isEnlargeOrRestore = true
            getWidthAndHeight(mCurrentMatrix)
            scale = DOUBLE_CLICK_SCALE
            val imageWidth = mImageWidth * DOUBLE_CLICK_SCALE
            val imageHeight = mImageHeight * DOUBLE_CLICK_SCALE
            tranX = -imageWidth / 4
            tranY = -imageHeight / 4
            // startAnimator();
            startAnimator(tranX, tranY, scale, object : OnAnimatorEnd {
                override fun onAnimatorEnd() {
                    mode = ENLARGE
                }
            })
        }
    }

    /**
     * 在缩放后的动画效果
     */
    fun after() {
        if (!isLoaded) {
            return
        }
        mCurrentMatrix!!.getValues(values)
        val mTranX = values[Matrix.MTRANS_X]
        val mTranY = values[Matrix.MTRANS_Y]
        val mScale = values[Matrix.MSCALE_X]
        getWidthAndHeight(mCurrentMatrix)
        var imageWidth = mImageWidth
        var imageHeight = mImageHeight
        // 对缩放处理
        if (mScale < nolmalScale) {
            scale = nolmalScale / mScale
            imageWidth = mImageWidth * scale
            imageHeight = mImageHeight * scale
            isNarrowing = true
        } else {
            scale = 1f
        }
        // 对X轴移动处理
        if (mTranX > 0) {
            tranX = -mTranX // 反方向
        } else if (Math.abs(mTranX) > imageWidth - mViewWidth) {
            tranX = -(imageWidth - Math.abs(mTranX) - mViewWidth)
        }
        val targetY: Float
        // 对Y轴移动处理
        if (imageHeight > mViewHeight) {
            if (mTranY > 0) {
                tranY = -mTranY // 反方向
            } else if (abs(mTranY) > imageHeight - mViewHeight) {
                tranY = -(imageHeight - abs(mTranY) - mViewHeight)
            }
        } else if (imageHeight < mViewHeight) {
            targetY = mViewHeight / 2 - imageHeight / 2
            tranY = targetY - mTranY
        } else {
        }
        if (animator != null && animator!!.isRunning) {
            animator!!.cancel()
        }
        isAfter = true
        // startAnimator();
        startAnimator(tranX, tranY, scale, null)
    }

    /**
     * 启动动画
     *
     * @param tranX
     * @param tranY
     * @param scale
     * @param end
     */
    private fun startAnimator(tranX: Float, tranY: Float, scale: Float, end: OnAnimatorEnd?) {
        if (animator != null && animator!!.isRunning) {
            animator!!.cancel()
        }
        mSaveMatrix!!.set(mCurrentMatrix)
        animator = ValueAnimator.ofFloat(1f)
        animator!!.duration = 300
        animator!!.addUpdateListener(MyAnimatorUpdateListener(tranX, tranY, scale, end, this))
        animator!!.start()
        isAnimating = true
    }

    /**
     * 执行动画效果
     */
    internal class MyAnimatorUpdateListener(
        private val mTranX: Float,
        private val mTranY: Float,
        private val mScale: Float,
        private val end: OnAnimatorEnd?,
        private var mZoomImageView: ZoomImageView?
    ) :
        AnimatorUpdateListener {
        override fun onAnimationUpdate(animation: ValueAnimator) {
            val value = animation.animatedValue as Float
            mZoomImageView?.mCurrentMatrix?.set(mZoomImageView?.mSaveMatrix)
            mZoomImageView?.getInitPoint(mZoomImageView?.midPoint)
            if (mScale != 1f) {
                val scale = 1 + (mScale - 1) * value
                mZoomImageView?.mCurrentMatrix?.postScale(
                    scale,
                    scale,
                    mZoomImageView?.midPoint!!.x,
                    mZoomImageView?.midPoint!!.y
                )
            }
            mZoomImageView?.mCurrentMatrix?.postTranslate(mTranX * value, mTranY * value)
            mZoomImageView?.imageMatrix = mZoomImageView?.mCurrentMatrix
            if (value == 1f) { // 动画结束
                mZoomImageView?.isAnimating = false
                mZoomImageView?.isNarrowing = false
                mZoomImageView?.tranX = 0f
                mZoomImageView?.tranY = 0f
                mZoomImageView?.scale = 1f
                end?.onAnimatorEnd()
            }
            mZoomImageView = null
        }
    }

    /**
     * 动画结束时执行
     *
     * @author lihanguang
     */
    interface OnAnimatorEnd {
        fun onAnimatorEnd()
    }

    /**
     * 拖动图片,进入拖拽模式，水平必定可拖动，竖直需要判断
     *
     * @param event
     */
    private fun dragImage(event: MotionEvent) {
        val currentX = event.x // 获取当前的x坐标
        val currentY = event.y // 获取当前的Y坐标
        var tranX = currentX - startPoint!!.x // 移动的x轴距离
        var tranY = currentY - startPoint!!.y // 移动的y轴距离
        // 判断是否达到滑动条件
        if (Math.abs(tranX - lastTranX) < MIN_DRAG && Math.abs(tranY - lastTranY) < MIN_DRAG) {
            tranX = lastTranX
            tranY = lastTranY
        }
        // 判断是否能够拖动
        if (!ableTranY) {
            tranY = 0f
        }
        if (!ableTranX) {
            tranX = 0f
        }
        mCurrentMatrix!!.postTranslate(tranX, tranY)

        // 判断是否拦截事件，当拖动到边界的时候不拦截
        getWidthAndHeight(mCurrentMatrix)
        mCurrentMatrix!!.getValues(values)
        val mTranX = values[Matrix.MTRANS_X]
        val ableToTurnLeft = mTranX > 3 && isInterruptLeft
        val ableToTurnRight =
            Math.abs(mTranX) > Math.abs(mImageWidth - mViewWidth) + 3 && isInterruptRight
        Log.d(TAG, "mTranX:$mTranX")
        Log.d(TAG, "mImageWidth:$mImageWidth")
        Log.d(TAG, "mViewWidth:$mViewWidth")
        //将滑动控制权返回给父view同时调整位置
        if (ableToTurnLeft) {
            Log.d(TAG, "Interrupt")
            isJudgeInterrputTouch = true
            values[Matrix.MTRANS_X] = 0F
            setInterrupt(false)
            mCurrentMatrix!!.setValues(values)
        } else if (ableToTurnRight) {
            Log.d(TAG, "Interrupt")
            isJudgeInterrputTouch = true
            values[Matrix.MTRANS_X] = mViewWidth - mImageWidth
            mCurrentMatrix!!.setValues(values)
            setInterrupt(false)
        } else {
            isJudgeInterrputTouch = false
        }
        imageMatrix = mCurrentMatrix
        // 记录上一次移动的位置
        lastTranX = tranX
        lastTranY = tranY
    }

    /**
     * 缩放图片
     *
     * @param event
     */
    private fun zoomImage(event: MotionEvent) {
        if (event.pointerCount < 2) {
            return
        }
        Log.d(TAG, "zoomImage")
        val newDistance = spacing(event) // 获取新的距离
        var scale = newDistance / oldDistance // 缩放比例
        scale = getScaleType(scale) // 获取缩放的类型
        midPoint(midPoint, event)
        mCurrentMatrix!!.postScale(scale, scale, midPoint!!.x, midPoint!!.y)
        imageMatrix = mCurrentMatrix
        getSizeMode() // 获得大小模式
    }

    /**
     * 判断是否可以缩放，超过最大缩放值不可缩放
     *
     * @return scale
     */
    private fun getScaleType(scale: Float): Float {
        val values = FloatArray(9)
        mCurrentMatrix!!.getValues(values)
        val nextScale = scale * values[Matrix.MSCALE_X]
        if (nextScale >= maxScale) {
            return maxScale / values[Matrix.MSCALE_X]
        } else if (nextScale <= minScale) {
            return minScale / values[Matrix.MSCALE_X]
        }
        return scale
    }

    /**
     * 计算两点的距离
     *
     * @param event
     * @return
     */
    private fun spacing(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return sqrt((x * x + y * y).toDouble()).toFloat()
    }

    /**
     * 计算缩放中点
     *
     * @param point
     * @param event
     */
    private fun midPoint(point: PointF?, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point!![x / 2] = y / 2
    }

    /**
     * 获取最大的放大倍数，值为初始化后宽度的一倍
     *
     * @return
     */
    private val maxScale: Float
        get() {
            val values = FloatArray(9)
            mInitMatrix!!.getValues(values)
            return MAX_SCALE * values[Matrix.MSCALE_X]
        }

    /**
     * 获取最小的放大倍数，值为初始化后宽度的0.5倍
     *
     * @return
     */
    private val minScale: Float
        get() {
            val values = FloatArray(9)
            mInitMatrix!!.getValues(values)
            return MIN_SCALE * values[Matrix.MSCALE_X]
        }

    /**
     * 获取初始化时候的缩放倍数
     *
     * @return
     */
    private val nolmalScale: Float
        get() {
            val values = FloatArray(9)
            mInitMatrix!!.getValues(values)
            return values[Matrix.MSCALE_X]
        }

    /**
     * 获得大小状态
     *
     * @return
     */
    private fun getSizeMode() {
        val values = FloatArray(9)
        mCurrentMatrix!!.getValues(values)
        getWidthAndHeight(mCurrentMatrix)
        val currentScale = values[Matrix.MSCALE_X]
        mInitMatrix!!.getValues(values)
        val initScale = values[Matrix.MSCALE_X]
        sizeMode = if (currentScale > initScale) {
            ENLARGE
            //			ableTranX = true;
        } else if (currentScale < initScale) {
            NARROW
            //			ableTranX = false;
            //			ableTranY = false;
        } else {
            NONE
            //			ableTranX = false;
        }

        // 判断是否可以滑动
        ableTranY = mImageHeight > mViewHeight
        ableTranX = mImageWidth > mViewWidth
    }

    private fun getInitPoint(pointf: PointF?) {
        val values = FloatArray(9)
        mCurrentMatrix!!.getValues(values)
        pointf!!.x = values[Matrix.MTRANS_X]
        pointf.y = values[Matrix.MTRANS_Y]
    }

    /**
     * 判断是否在最右边或最左边
     */
    private val isInRightOrLeft: Unit
        get() {
            val values = FloatArray(9)
            mCurrentMatrix!!.getValues(values)
            val mTranX = values[Matrix.MTRANS_X]
            getWidthAndHeight(mCurrentMatrix)
            inLeft = mTranX >= 0
            inRight = abs(mTranX) > mImageWidth - mViewWidth
        }

    /**
     * 设置是否拦截左滑动
     *
     * @param interrupt
     */
    fun setInterruptLeft(interrupt: Boolean) {
        isInterruptLeft = interrupt
    }

    /**
     * 设置是否拦截右滑动
     *
     * @param interrupt
     */
    fun setInterruptRight(interrupt: Boolean) {
        isInterruptRight = interrupt
    }

    /**
     * 设置单击事件
     *
     * @param onSingleClick
     */
    fun setOnSingleClick(onSingleClick: OnSingleClick?) {
        mOnSingleClick = onSingleClick
    }

    /**
     * @param isInterrupt true时父View不拦截事件，false时View可以拦截事件
     */
    private fun setInterrupt(isInterrupt: Boolean) {
        if (parent != null) {
            parent!!.requestDisallowInterceptTouchEvent(isInterrupt)
        }
    }

    /**
     * 单击监听器
     *
     * @author lihanguang
     */
    interface OnSingleClick {
        fun onClick(v: View?)
    }

    /**
     * 单击事件的延迟执行
     */
    private val delayOnClick = Runnable {
        if (mOnSingleClick != null) {
            mOnSingleClick!!.onClick(thisImageView)
        }
    }

    /**
     * 触摸事件 处理手势
     *
     * @author
     */
    private inner class ZoomListener : OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_POINTER_DOWN -> {
                    getSizeMode()
                    oldDistance = spacing(event)
                    Log.d(TAG, "oldDistance:$oldDistance")
                    if (oldDistance > 10f) {
                        mode = ZOOM
                    }
                }
                MotionEvent.ACTION_MOVE -> if (mode == ZOOM) { // 缩放模式
                    Log.d(TAG, "mode:$mode")
                    mCurrentMatrix!!.set(mSaveMatrix)
                    zoomImage(event)
                }
                MotionEvent.ACTION_UP -> {
                    // 拖拽模式
                    if (mode == DRAG) {
                        after()
                        mode = NONE
                        // 缩放模式
                    } else if (mode == ZOOM) {
                        after()
                        mode = NONE
                    } else if (mode == DOUBLECLICK) {
                        mode = NONE
                    }
                    getParent().requestDisallowInterceptTouchEvent(true)
                }
                MotionEvent.ACTION_POINTER_UP -> if (event.pointerCount == 2) {
                    after()
                }
                else -> {
                }
            }
            val consume = mGestureDetector!!.onTouchEvent(event)
            return true
        }
    }

    /**
     * GestureDetector的事件
     *
     * @param
     * @return
     */
    var simpleOnGestureListener: SimpleOnGestureListener? = null

    private fun initSimpleOnGestureListener() {
        simpleOnGestureListener = object : SimpleOnGestureListener() {
            /**
             * 第一次按下
             */
            override fun onDown(e: MotionEvent): Boolean {
                getSizeMode()
                Log.d(TAG, "mode:$mode")
                setInterrupt(true)
                if (sizeMode == NONE && !ableTranY) {
                    setInterrupt(false)
                }
                if (isAnimating) {
                    if (isNarrowing || mode == DOUBLECLICK) {
                        return true
                    } else {
                        animator!!.cancel()
                    }
                }
                mSaveMatrix!!.set(mCurrentMatrix)
                getSizeMode()
                if (sizeMode == ENLARGE || ableTranY) { // 放大或Y轴可拖动
                    mode = DRAG // 单点进入拖动模式
                    startPoint!![e.x] = e.y
                    lastPoint!![e.x] = e.y
                }
                return true
            }

            /**
             * 处理拖动
             */
            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if (mode == DRAG) { // 拖动模式
                    mCurrentMatrix!!.set(mSaveMatrix)
                    dragImage(e2)
                }
                return true
            }

            /**
             * 滑动
             */
            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                scroll(velocityX, velocityY)
                return true
            }

            /**
             * 双击事件
             */
            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (sizeMode == ENLARGE) {
                    restore()
                } else if (sizeMode == NONE) {
                    enlarge()
                }
                removeCallbacks(delayOnClick)
                mode = DOUBLECLICK
                return true
            }

            /**
             * 单击事件
             */
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                postDelayed(delayOnClick, DELAY_TIME_TO_ONCLICK.toLong())
                return true
            }
        }
    }

    companion object {
        // 手势模式
        /**
         * 初始化状态
         */
        private const val NONE = 0

        /**
         * 拖动模式，在放大的情况下
         */
        private const val DRAG = 1

        /**
         * 缩放模式
         */
        private const val ZOOM = 2

        /**
         * 双击模式
         */
        private const val DOUBLECLICK = 4
        // 缩放模式 PS：NONE表示初始化
        /**
         * 放大状态
         */
        private const val ENLARGE = 5

        /**
         * 缩小状态
         */
        private const val NARROW = 6

        /**
         * 最大放大倍数
         */
        const val MAX_SCALE = 4f

        /**
         * 最小缩放倍数
         */
        const val MIN_SCALE = 0.5f

        /**
         * 最小拖拽距离
         */
        const val MIN_DRAG = 8f

        /**
         * 点击放大的倍数
         */
        const val DOUBLE_CLICK_SCALE = 2f

        /**
         * 单击事件的延迟时间
         */
        private const val DELAY_TIME_TO_ONCLICK = 300

        /**
         * 最小的滑动速度
         */
        private const val MIN_SCROLL_SPEED = 1000
        private const val TAG = "fiend"
    }
}