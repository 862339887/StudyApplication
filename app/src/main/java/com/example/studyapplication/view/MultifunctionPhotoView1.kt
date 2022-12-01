package com.example.studyapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import java.util.*

class MultifunctionPhotoView1 @JvmOverloads constructor(
    ctx: Context? = null,
    attrs: AttributeSet? = null,
    def: Int = -1
) : AppCompatImageView(ctx!!, attrs, def) {
    private var attacher: PhotoViewAttcher1? = null
    private var pendingScaleType: ScaleType? = null

    /**
     * 绘制裁剪边框的条件，根据输入条件计算出初始值进行初次绘制，
     * 然后根据边框的拖动实时更改ClipBorderCondition
     */
    class ClipBorderCondition {
        var type: ClipBorderType? = null
        var color = 0
        var width = 0
        var appendWidth = 0
        var layoutLeft = 0
        var layoutTop = 0
        var layoutRight = 0
        var layoutBottom = 0
        var minLayoutHeight = 0
        var minLayoutWidth = 0
        var showWidthHeightValue = false
    }

    /**
     * 输入条件
     */
    private var inputCondition: InputCondition? = null

    /**
     * 是否绘制输入条件
     */
    private var drawInputCondition = false

    private var clipBorderCondition: ClipBorderCondition? = null

    private var bmScale //图片的缩小比例
            = 0f
    private var drawBitmap //缩小后的图片
            : Bitmap? = null
    private var selfWidth = 0
    private var selfHeight: Int = 0
    private var parentWidth = 0
    private var parentHeight: Int = 0

    init {
        init()
    }

    private fun init() {
        attacher = PhotoViewAttcher1(this)
        //We always pose as a Matrix scale type, though we can change to another scale type
        //via the attacher
        super.setScaleType(ScaleType.MATRIX)
        //apply the previously applied scale type
        if (pendingScaleType != null) {
            setScaleType(pendingScaleType)
            pendingScaleType = null
        }
    }

    fun getAttacher(): PhotoViewAttcher1? {
        return attacher
    }

    override fun getScaleType(): ScaleType? {
        return attacher!!.getScaleType()
    }

    override fun getImageMatrix(): Matrix? {
        return attacher!!.getImageMatrix()
    }

    override fun setOnLongClickListener(l: OnLongClickListener?) {
        attacher!!.setOnLongClickListener(l)
    }

    override fun setOnClickListener(l: View.OnClickListener?) {
        attacher!!.setOnClickListener(l)
    }

    override fun setScaleType(scaleType: ScaleType?) {
        if (attacher == null) {
            pendingScaleType = scaleType
        } else {
            attacher!!.mScaleType = scaleType!!
        }
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        // setImageBitmap calls through to this method
        if (attacher != null) {
            attacher!!.update()
        }
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        if (attacher != null) {
            attacher!!.update()
        }
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        if (attacher != null) {
            attacher!!.update()
        }
    }

    protected override fun setFrame(l: Int, t: Int, r: Int, b: Int): Boolean {
        val changed: Boolean = super.setFrame(l, t, r, b)
        if (changed) {
            attacher!!.update()
        }
        return changed
    }

    fun setRotationTo(rotationDegree: Float) {
        attacher!!.setRotationTo(rotationDegree)
    }

    fun setRotationBy(rotationDegree: Float) {
        attacher!!.setRotationBy(rotationDegree)
    }

    fun isZoomable(): Boolean {
        return attacher!!.isZoomable()
    }

    fun setZoomable(zoomable: Boolean) {
        attacher!!.mZoomEnabled = zoomable
    }

    fun getDisplayRect(): RectF? {
        return attacher!!.getDisplayRect()
    }

    fun getDisplayMatrix(matrix: Matrix?) {
        attacher!!.getDisplayMatrix(matrix!!)
    }

    fun setDisplayMatrix(finalRectangle: Matrix?): Boolean {
        return attacher!!.setDisplayMatrix(finalRectangle)
    }

    fun getSuppMatrix(matrix: Matrix?) {
        attacher!!.getSuppMatrix(matrix!!)
    }

    fun setSuppMatrix(matrix: Matrix?): Boolean {
        return attacher!!.setDisplayMatrix(matrix)
    }

    fun getMinimumScale(): Float {
        return attacher!!.getMinimumScale()
    }

    fun getMediumScale(): Float {
        return attacher!!.getMediumScale()
    }

    fun getMaximumScale(): Float {
        return attacher!!.getMaximumScale()
    }

    fun getScale(): Float {
        return attacher!!.getScale()
    }

    fun setAllowParentInterceptOnEdge(allow: Boolean) {
        attacher!!.setAllowParentInterceptOnEdge(allow)
    }

    fun setMinimumScale(minimumScale: Float) {
        attacher!!.mMinScale = minimumScale
    }

    fun setMediumScale(mediumScale: Float) {
        attacher!!.mMidScale = mediumScale
    }

    fun setMaximumScale(maximumScale: Float) {
        attacher!!.mMaxScale = maximumScale
    }

    fun setScaleLevels(minimumScale: Float, mediumScale: Float, maximumScale: Float) {
        attacher!!.setScaleLevels(minimumScale, mediumScale, maximumScale)
    }

    fun setOnMatrixChangeListener(listener: OnMatrixChangedListener?) {
        attacher!!.setOnMatrixChangeListener(listener)
    }

    fun setOnPhotoTapListener(listener: OnPhotoTapListener?) {
        attacher!!.setOnPhotoTapListener(listener)
    }

    fun setOnOutsidePhotoTapListener(listener: OnOutsidePhotoTapListener?) {
        attacher!!.setOnOutsidePhotoTapListener(listener)
    }

    fun setOnViewTapListener(listener: OnViewTapListener?) {
        attacher!!.setOnViewTapListener(listener)
    }

    fun setOnViewDragListener(listener: OnViewDragListener?) {
        attacher!!.setOnViewDragListener(listener)
    }

    fun setScale(scale: Float) {
        attacher!!.mScale = scale
    }

    fun setScale(scale: Float, animate: Boolean) {
        attacher!!.setScale(scale, animate)
    }

    fun setScale(scale: Float, focalX: Float, focalY: Float, animate: Boolean) {
        attacher!!.setScale(scale, focalX, focalY, animate)
    }

    fun setZoomTransitionDuration(milliseconds: Int) {
        attacher!!.setZoomTransitionDuration(milliseconds)
    }

    fun setOnDoubleTapListener(onDoubleTapListener: GestureDetector.OnDoubleTapListener?) {
        attacher!!.setOnDoubleTapListener(onDoubleTapListener)
    }

    fun setOnScaleChangeListener(onScaleChangedListener: OnScaleChangedListener?) {
        attacher!!.setOnScaleChangeListener(onScaleChangedListener)
    }

    fun setOnSingleFlingListener(onSingleFlingListener: OnSingleFlingListener?) {
        attacher!!.setOnSingleFlingListener(onSingleFlingListener)
    }


    var DEBUG = false

    private val TAG = javaClass.simpleName

    /**
     * 裁剪边框的类型
     */
    enum class ClipBorderType {
        Rectangle
    }

    /**
     * 输入条件，ImageClipView根据输入条件绘制图片和裁剪边框
     */
    class InputCondition {
        /**
         * 将要裁剪的原始图片
         */
        var rawBitmap: Bitmap? = null
        var rawBitmapWidth = 0
        var rawBitmapHeight = 0

        /**
         * 裁剪边框的类型
         */
        var clipBorderType: ClipBorderType? = null

        /**
         * 裁剪边框的颜色
         */
        var clipBorderColor = 0

        /**
         * 裁剪边框的宽度
         */
        var clipBorderWidth = 0

        /**
         * 判定触摸裁剪边框的附加宽度（像素）
         */
        var clipBorderAppendWidth = 0

        /**
         * 裁剪框的最小高度
         */
        var clipBorderLayoutMinHeight = 0

        /**
         * 裁剪框的最小宽度
         */
        var clipBorderLayoutMinWidth = 0

        /**
         * 是否显示宽高的值
         */
        var showWidthHeightValue = false

        /**
         * 输入条件的构造器
         */
        class Builder {
            private val DEFAULT_MIN_W = 100
            private val DEFAULT_MIN_H = 100
            private var rawBitmap: Bitmap? = null
            private var clipBorderType = ClipBorderType.Rectangle
            private var clipBorderColor = Color.WHITE
            private var clipBorderWidth = 15
            private var clipBorderAppendWidth = 20
            private var clipBorderLayoutMinHeight = DEFAULT_MIN_H
            private var clipBorderLayoutMinWidth = DEFAULT_MIN_W
            private var showWidthHeightValue = true
            fun build(): InputCondition {
                val condition = InputCondition()
                if (rawBitmap != null) {
                    condition.rawBitmap = rawBitmap
                    condition.rawBitmapHeight = rawBitmap!!.height
                    condition.rawBitmapWidth = rawBitmap!!.width
                    //                    condition.rawBitmapHeight = 2000;
//                    condition.rawBitmapWidth = 1000;
                }
                condition.clipBorderType = clipBorderType
                condition.clipBorderColor = clipBorderColor
                condition.clipBorderWidth = clipBorderWidth
                condition.clipBorderAppendWidth = clipBorderAppendWidth
                condition.clipBorderLayoutMinHeight =
                    Math.max(clipBorderLayoutMinHeight, DEFAULT_MIN_H)
                condition.clipBorderLayoutMinWidth =
                    Math.max(clipBorderLayoutMinWidth, DEFAULT_MIN_W)
                condition.showWidthHeightValue = showWidthHeightValue
                return condition
            }

            fun setRawBitmap(rawBitmap: Bitmap?): Builder {
                this.rawBitmap = rawBitmap
                return this
            }

            fun setClipBorderType(clipBorderType: ClipBorderType): Builder {
                this.clipBorderType = clipBorderType
                return this
            }

            fun setClipBorderColor(clipBorderColor: Int): Builder {
                this.clipBorderColor = clipBorderColor
                return this
            }

            fun setClipBorderWidth(clipBorderWidth: Int): Builder {
                this.clipBorderWidth = clipBorderWidth
                return this
            }

            fun setClipBorderAppendWidth(clipBorderAppendWidth: Int): Builder {
                this.clipBorderAppendWidth = clipBorderAppendWidth
                return this
            }

            fun setClipBorderLayoutMinWidth(clipBorderLayoutMinWidth: Int): Builder {
                this.clipBorderLayoutMinWidth = clipBorderLayoutMinWidth
                return this
            }

            fun setClipBorderLayoutMinHeight(clipBorderLayoutMinHeight: Int): Builder {
                this.clipBorderLayoutMinHeight = clipBorderLayoutMinHeight
                return this
            }

            fun setShowWidthHeightValue(showWidthHeightValue: Boolean): Builder {
                this.showWidthHeightValue = showWidthHeightValue
                return this
            }
        }
    }


    /**
     * 设置输入条件
     *
     * @param inputCondition 输入条件
     */
    fun setInputCondition(inputCondition: InputCondition?) {
        this.inputCondition = inputCondition
    }

    /**
     * 根据输入条件执行绘制
     *
     * @param delay 延时绘制的时间值，单位毫秒
     */
    fun executeDrawInputCondition(delay: Long) {
        val runnable = Runnable { redrawImageClipView() }
        if (delay <= 0) {
            post(runnable)
        } else {
            postDelayed(runnable, delay)
        }
    }

    /**
     * 创建
     */
    fun onCreate(condition: InputCondition?, delay: Long) {
        setInputCondition(condition)
        executeDrawInputCondition(delay)
    }

    /**
     * 释放资源
     */
    fun onDestroy() {
        setBackgroundColor(Color.TRANSPARENT)
        if (drawBitmap != null) {
            drawBitmap!!.recycle()
            drawBitmap = null
        }
        clipBorderCondition = null
        inputCondition = null
    }

    /**
     * 获取裁剪区域的图片
     *
     * @param inBorder 裁剪的区域是否为不包含裁剪框线的内区域
     */
    fun getClippedBitmap(inBorder: Boolean): Bitmap? {
        return if (clipBorderCondition!!.type == ClipBorderType.Rectangle) {
            getClippedBitmapForRect(inBorder)
        } else null
    }

    /**
     * 获取矩形裁剪框区域的图片
     */
    private fun getClippedBitmapForRect(inBorder: Boolean): Bitmap? {
        var left = clipBorderCondition!!.layoutLeft
        var top = clipBorderCondition!!.layoutTop
        var right = clipBorderCondition!!.layoutRight
        var bottom = clipBorderCondition!!.layoutBottom
        if (inBorder && bmScale != 1f) {
            val strokeW = clipBorderCondition!!.width
            left = ((left + strokeW) / bmScale).toInt()
            top = ((top + strokeW) / bmScale).toInt()
            right = ((right - strokeW) / bmScale).toInt()
            bottom = ((bottom - strokeW) / bmScale).toInt()
        }
        return Bitmap.createBitmap(
            inputCondition!!.rawBitmap!!,
            left,
            top,
            right - left,
            bottom - top
        )
    }

    //重绘
    private fun redrawImageClipView() {
        val parent = getParent() as ViewGroup
        parentWidth = parent.width
        parentHeight = parent.height
        if (parentWidth == 0 && parentHeight == 0) {
            executeDrawInputCondition(10)
            return
        }
        if (DEBUG) {
            Log.d(TAG, "redrawImageClipView: parentWidth=$parentWidth")
            Log.d(TAG, "redrawImageClipView: parentHeight=$parentHeight")
        }
        calculateSelfWidthHeightAndScaleBitmap(inputCondition)
        calculateClipperBorder(inputCondition)
        drawInputCondition = true
        postInvalidate()
    }

    //根据图片的宽高来计算自身的宽高，以及需要绘制的新图片的宽高
    private fun calculateSelfWidthHeightAndScaleBitmap(condition: InputCondition?) {
        val oldBmW = condition!!.rawBitmapWidth
        val oldBmH = condition.rawBitmapHeight

        //图片宽 <= 容器宽
        if (oldBmW <= parentWidth) {
            val scale: Float = 1f * oldBmH / parentHeight
            if (DEBUG) {
                Log.d(TAG, "calculateSelfWidthHeightAndScaleBitmap: 1: scale=$scale")
            }
            selfWidth = if (scale <= 1) oldBmW else (oldBmW / scale).toInt()
            selfHeight = if (scale <= 1) oldBmH else (oldBmH / scale).toInt()
            bmScale = if (scale <= 1f) 1f else 1f / scale
        } else {
            val scale = 1f * parentWidth / oldBmW
            if (DEBUG) {
                Log.d(TAG, "calculateSelfWidthHeightAndScaleBitmap: 2: scale=$scale")
            }
            selfWidth = parentWidth
            selfHeight = (oldBmH * scale).toInt()
            bmScale = scale
        }
        if (DEBUG) {
            Log.d(TAG, "calculateSelfWidthHeightAndScaleBitmap: bmScale=$bmScale")
            Log.d(TAG, "calculateSelfWidthHeightAndScaleBitmap: selfWidth=$selfWidth")
            Log.d(TAG, "calculateSelfWidthHeightAndScaleBitmap: selfHeight=$selfHeight")
        }
        val matrix = Matrix()
        matrix.setScale(bmScale, bmScale)
        drawBitmap = Bitmap.createBitmap(
            condition.rawBitmap!!,
            0,
            0,
            condition.rawBitmapWidth,
            condition.rawBitmapHeight,
            matrix,
            true
        )
        val left = (parentWidth - selfWidth) / 2
        val top: Int = (parentHeight - selfHeight) / 2
        val right = left + selfWidth
        val bottom: Int = top + selfHeight
        layout(left, top, right, bottom)
    }

    //根据输入条件和自身的宽高，来计算裁剪框布局
    private fun calculateClipperBorder(condition: InputCondition?) {
        clipBorderCondition = ClipBorderCondition()
        clipBorderCondition!!.type = condition!!.clipBorderType
        clipBorderCondition!!.color = condition.clipBorderColor
        clipBorderCondition!!.width = condition.clipBorderWidth
        clipBorderCondition!!.appendWidth = condition.clipBorderAppendWidth
        clipBorderCondition!!.layoutLeft = 0
        clipBorderCondition!!.layoutTop = 0
        clipBorderCondition!!.layoutRight = selfWidth
        clipBorderCondition!!.layoutBottom = selfHeight
        clipBorderCondition!!.minLayoutHeight = condition.clipBorderLayoutMinHeight
        clipBorderCondition!!.minLayoutWidth = condition.clipBorderLayoutMinWidth
        clipBorderCondition!!.showWidthHeightValue = condition.showWidthHeightValue
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!drawInputCondition) return

        //如果输入条件为空，则不根据输入条件进行绘制
        if (inputCondition == null) return
        if (!canvasDrawBitmap(canvas)) {
            Toast.makeText(getContext(), "请正确设置裁剪图片", Toast.LENGTH_SHORT).show()
            return
        }
        if (!canvasClipBorder(canvas)) {
            Toast.makeText(getContext(), "请正确设置裁剪边框", Toast.LENGTH_SHORT).show()
        }
    }

    private fun canvasDrawBitmap(canvas: Canvas): Boolean {
        if (drawBitmap == null) return false
        canvas.drawBitmap(drawBitmap!!, 0f, 0f, null)
        return true
    }

    private fun canvasClipBorder(canvas: Canvas): Boolean {
        if (clipBorderCondition == null) return false
        return if (clipBorderCondition!!.type == ClipBorderType.Rectangle) {
            drawRectangleClipBorder(canvas)
        } else false
    }

    private fun drawRectangleClipBorder(canvas: Canvas): Boolean {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = clipBorderCondition!!.color
        paint.strokeWidth = clipBorderCondition!!.width.toFloat()
        val halfWidth = 1f * clipBorderCondition!!.width / 2
        val left = (clipBorderCondition!!.layoutLeft + halfWidth).toInt()
        val top = (clipBorderCondition!!.layoutTop + halfWidth).toInt()
        val right = (clipBorderCondition!!.layoutRight - halfWidth).toInt()
        val bottom = (clipBorderCondition!!.layoutBottom - halfWidth).toInt()
        canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        if (clipBorderCondition!!.showWidthHeightValue) {
            paint.reset()
            paint.isAntiAlias = true
            paint.textSize = 25f
            paint.color = clipBorderCondition!!.color
            val fmt = "w:%d, h:%d"
            val text = String.format(Locale.getDefault(), fmt, right - left, bottom - top)
            canvas.drawText(
                text,
                (left + clipBorderCondition!!.width).toFloat(),
                (top + clipBorderCondition!!.width + 20).toFloat(),
                paint
            )
        }
        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (clipBorderCondition?.type == ClipBorderType.Rectangle) {
            handleRectangleClipBorderOnDrawWhenDrag(event)
        } else super.onTouchEvent(event)
    }

    private var moveX_Rect = 0f
    private var moveY_Rect: kotlin.Float = 0f
    private var touchInBorderStrokeLeft_Rect = false
    private var touchInBorderStrokeTop_Rect = false
    private var touchInBorderStrokeRight_Rect = false
    private var touchInBorderStrokeBottom_Rect = false
    private var touchInBorderStrokeLeftTop_Rect = false
    private var touchInBorderStrokeLeftBottom_Rect = false
    private var touchInBorderStrokeRightTop_Rect = false
    private var touchInBorderStrokeRightBottom_Rect = false
    private var touchInBorderStroke_Rect = false

    //处理矩形裁剪框的拖拽
    private fun handleRectangleClipBorderOnDrawWhenDrag(event: MotionEvent): Boolean {
        var downY: Float
        var downX: Float
        if (event.action == MotionEvent.ACTION_DOWN) {
//            moveX_Rect = downX = event.getX();
//            moveY_Rect = downY = event.getY();
//
//            int touchWidth = clipBorderCondition.width + clipBorderCondition.appendWidth;
//            int _left = clipBorderCondition.layoutLeft;
//            int _top = clipBorderCondition.layoutTop;
//            int _right = clipBorderCondition.layoutRight;
//            int _bottom = clipBorderCondition.layoutBottom;
//
//            touchInBorderStrokeLeft_Rect = Math.abs(downX - _left) <= touchWidth;
//            touchInBorderStrokeTop_Rect = Math.abs(downY - _top) <= touchWidth;
//            touchInBorderStrokeRight_Rect = Math.abs(downX - _right) <= touchWidth;
//            touchInBorderStrokeBottom_Rect = Math.abs(downY - _bottom) <= touchWidth;
//
//            touchInBorderStrokeLeftTop_Rect = touchInBorderStrokeLeft_Rect && touchInBorderStrokeTop_Rect;
//            touchInBorderStrokeLeftBottom_Rect = touchInBorderStrokeLeft_Rect && touchInBorderStrokeBottom_Rect;
//            touchInBorderStrokeRightTop_Rect = touchInBorderStrokeRight_Rect && touchInBorderStrokeTop_Rect;
//            touchInBorderStrokeRightBottom_Rect = touchInBorderStrokeRight_Rect && touchInBorderStrokeBottom_Rect;
//
//            touchInBorderStroke_Rect = touchInBorderStrokeLeft_Rect ||
//                    touchInBorderStrokeTop_Rect ||
//                    touchInBorderStrokeRight_Rect ||
//                    touchInBorderStrokeBottom_Rect /*||
//                    touchInBorderStrokeLeftTop_Rect ||
//                    touchInBorderStrokeLeftBottom_Rect ||
//                    touchInBorderStrokeRightTop_Rect ||
//                    touchInBorderStrokeRightBottom_Rect*/;
//
//            if (DEBUG) {
//                Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: DOWN: downX=" + downX + ", downY=" + downY + ", touchInBorderStroke=" + touchInBorderStroke_Rect);
//            }
            return true
        } else if (event.action == MotionEvent.ACTION_UP) {
            moveY_Rect = 0f
            moveX_Rect = moveY_Rect
            touchInBorderStroke_Rect = false
            touchInBorderStrokeLeftTop_Rect = false
            touchInBorderStrokeLeftBottom_Rect = false
            touchInBorderStrokeRightTop_Rect = false
            touchInBorderStrokeRightBottom_Rect = false
            touchInBorderStrokeLeft_Rect = false
            touchInBorderStrokeTop_Rect = false
            touchInBorderStrokeRight_Rect = false
            touchInBorderStrokeBottom_Rect = false
            return true
        } else if (event.action == MotionEvent.ACTION_MOVE) {
            val mX = event.x
            val mY = event.y
            if (DEBUG) {
                Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: MOVE: mX=$mX, mY=$mY")
            }

            //处理四边和四角拖动
            if (touchInBorderStroke_Rect) {
                if (DEBUG) {
                    Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: ACTION_MOVE: 1")
                }
                val _minW = clipBorderCondition!!.minLayoutWidth + clipBorderCondition!!.width
                val _minH = clipBorderCondition!!.minLayoutHeight + clipBorderCondition!!.width
                val _left = clipBorderCondition!!.layoutLeft
                val _top = clipBorderCondition!!.layoutTop
                val _right = clipBorderCondition!!.layoutRight
                val _bottom = clipBorderCondition!!.layoutBottom

                //左上角
                if (touchInBorderStrokeLeftTop_Rect && mX >= 0 && mY >= 0 && mX <= _right - _minW && mY <= _bottom - _minH) {
                    clipBorderCondition!!.layoutLeft = mX.toInt()
                    clipBorderCondition!!.layoutTop = mY.toInt()
                } else if (touchInBorderStrokeLeftBottom_Rect && mX >= 0 && mY <= selfHeight && mX <= _right - _minW && mY >= _top + _minH) {
                    clipBorderCondition!!.layoutLeft = mX.toInt()
                    clipBorderCondition!!.layoutBottom = mY.toInt()
                } else if (touchInBorderStrokeRightTop_Rect && mX <= selfWidth && mY >= 0 && mX >= _left + _minW && mY <= _bottom - _minH) {
                    clipBorderCondition!!.layoutRight = mX.toInt()
                    clipBorderCondition!!.layoutTop = mY.toInt()
                } else if (touchInBorderStrokeRightBottom_Rect && mX <= selfWidth && mY <= selfHeight && mX >= _left + _minW && mY >= _top + _minH) {
                    clipBorderCondition!!.layoutRight = mX.toInt()
                    clipBorderCondition!!.layoutBottom = mY.toInt()
                } else if (touchInBorderStrokeLeft_Rect && mX >= 0 && mX <= _right - _minW) {
                    clipBorderCondition!!.layoutLeft = mX.toInt()
                } else if (touchInBorderStrokeTop_Rect && mY >= 0 && mY <= _bottom - _minH) {
                    clipBorderCondition!!.layoutTop = mY.toInt()
                } else if (touchInBorderStrokeRight_Rect && mX <= selfWidth && mX >= _left + _minW) {
                    clipBorderCondition!!.layoutRight = mX.toInt()
                } else if (touchInBorderStrokeBottom_Rect && mY <= selfHeight && mY >= _top + _minH) {
                    clipBorderCondition!!.layoutBottom = mY.toInt()
                }
                postInvalidate()
            } else {
                if (DEBUG) {
                    Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: ACTION_MOVE: 2")
                }
                val diffX = mX - moveX_Rect
                val diffY: Float = mY - moveY_Rect
                moveX_Rect = mX
                moveY_Rect = mY
                val _left = clipBorderCondition!!.layoutLeft + diffX
                val _top = clipBorderCondition!!.layoutTop + diffY
                val _right = clipBorderCondition!!.layoutRight + diffX
                val _bottom = clipBorderCondition!!.layoutBottom + diffY
                val _width = clipBorderCondition!!.layoutRight - clipBorderCondition!!.layoutLeft
                val _height = clipBorderCondition!!.layoutBottom - clipBorderCondition!!.layoutTop
                if (DEBUG) {
                    Log.v(
                        TAG,
                        "handleRectangleClipBorderOnDrawWhenDrag: _left=$_left, _top=$_top, _right=$_right, _bottom=$_bottom, _width=$_width, _height=$_height"
                    )
                }
                if (_left >= 0 && _top >= 0 && _right <= selfWidth && _bottom <= selfHeight) {
                    if (DEBUG) {
                        Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: ")
                    }
                    clipBorderCondition!!.layoutLeft = _left.toInt()
                    clipBorderCondition!!.layoutTop = _top.toInt()
                    clipBorderCondition!!.layoutRight = _right.toInt()
                    clipBorderCondition!!.layoutBottom = _bottom.toInt()
                    postInvalidate()
                }
            }
            return true
        }
        return super.onTouchEvent(event)
    }

     fun judgeIsClipPhoto(event: MotionEvent): Boolean {
        val downY: Float
        val downX: Float
        downX = event.x
        moveX_Rect = downX
        downY = event.y
        moveY_Rect = downY
        val touchWidth = clipBorderCondition!!.width + clipBorderCondition!!.appendWidth
        val _left = clipBorderCondition!!.layoutLeft
        val _top = clipBorderCondition!!.layoutTop
        val _right = clipBorderCondition!!.layoutRight
        val _bottom = clipBorderCondition!!.layoutBottom
        touchInBorderStrokeLeft_Rect = Math.abs(downX - _left) <= touchWidth
        touchInBorderStrokeTop_Rect = Math.abs(downY - _top) <= touchWidth
        touchInBorderStrokeRight_Rect = Math.abs(downX - _right) <= touchWidth
        touchInBorderStrokeBottom_Rect = Math.abs(downY - _bottom) <= touchWidth
        touchInBorderStrokeLeftTop_Rect =
            touchInBorderStrokeLeft_Rect && touchInBorderStrokeTop_Rect
        touchInBorderStrokeLeftBottom_Rect =
            touchInBorderStrokeLeft_Rect && touchInBorderStrokeBottom_Rect
        touchInBorderStrokeRightTop_Rect =
            touchInBorderStrokeRight_Rect && touchInBorderStrokeTop_Rect
        touchInBorderStrokeRightBottom_Rect =
            touchInBorderStrokeRight_Rect && touchInBorderStrokeBottom_Rect
        touchInBorderStroke_Rect = touchInBorderStrokeLeft_Rect ||
                touchInBorderStrokeTop_Rect ||
                touchInBorderStrokeRight_Rect ||
                touchInBorderStrokeBottom_Rect /*||
                    touchInBorderStrokeLeftTop_Rect ||
                    touchInBorderStrokeLeftBottom_Rect ||
                    touchInBorderStrokeRightTop_Rect ||
                    touchInBorderStrokeRightBottom_Rect*/
        return touchInBorderStroke_Rect
    }
}