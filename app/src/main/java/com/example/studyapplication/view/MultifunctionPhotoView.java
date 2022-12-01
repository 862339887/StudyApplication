/*
 Copyright 2011, 2012 Chris Banes.
 <p>
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 <p>
 http://www.apache.org/licenses/LICENSE-2.0
 <p>
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.example.studyapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import java.util.Locale;

@SuppressWarnings("unused")
public class MultifunctionPhotoView extends AppCompatImageView {

    private PhotoViewAttacher attacher;
    private ScaleType pendingScaleType;

    public Boolean initFlag = true;
    public Paint paintTest;

    public Boolean flag;
    public RectF testRect;

    /**
     * 绘制裁剪边框的条件，根据输入条件计算出初始值进行初次绘制，
     * 然后根据边框的拖动实时更改ClipBorderCondition
     */
    private static final class ClipBorderCondition {
        private ClipBorderType type;
        private int color;
        private int width;
        private int testHeight;
        private int appendWidth;
        private int layoutLeft;
        private int layoutTop;
        private int layoutRight;
        private int layoutBottom;
        private int minLayoutHeight;
        private int minLayoutWidth;
        private boolean showWidthHeightValue;
    }

    /**
     * 输入条件
     */
    private InputCondition inputCondition;

    /**
     * 是否绘制输入条件
     */
    private boolean drawInputCondition = false;

    private ClipBorderCondition clipBorderCondition;

    private float bmScale;//图片的缩小比例
    private Bitmap drawBitmap;//缩小后的图片
    private int selfWidth, selfHeight;//自身的宽高
    private int parentWidth, parentHeight;//父容器的宽高

    public MultifunctionPhotoView(Context context) {
        this(context, null);
    }

    public MultifunctionPhotoView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public MultifunctionPhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init();
    }

    public void postDrawRect(Boolean drawRect) {

    }

    private void init() {
        attacher = new PhotoViewAttacher(this);
        paintTest = new Paint();
        paintTest.setAntiAlias(true);
        paintTest.setStyle(Paint.Style.FILL);
        paintTest.setColor(Color.parseColor("#33000000"));
        paintTest.setAlpha(255);
        paintTest.setStrokeWidth(10);

        //We always pose as a Matrix scale type, though we can change to another scale type
        //via the attacher
        super.setScaleType(ScaleType.MATRIX);
        //apply the previously applied scale type
        if (pendingScaleType != null) {
            setScaleType(pendingScaleType);
            pendingScaleType = null;
        }
    }

    public PhotoViewAttacher getAttacher() {
        return attacher;
    }

    @Override
    public ScaleType getScaleType() {
        return attacher.getScaleType();
    }

    @Override
    public Matrix getImageMatrix() {
        return attacher.getImageMatrix();
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        attacher.setOnLongClickListener(l);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        attacher.setOnClickListener(l);
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (attacher == null) {
            pendingScaleType = scaleType;
        } else {
            attacher.setScaleType(scaleType);
        }
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        // setImageBitmap calls through to this method
        if (attacher != null) {
            attacher.update();
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (attacher != null) {
            attacher.update();
        }
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (attacher != null) {
            attacher.update();
        }
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        boolean changed = super.setFrame(l, t, r, b);
        if (changed) {
            attacher.update();
        }
        return changed;
    }

    public void setRotationTo(float rotationDegree) {
        attacher.setRotationTo(rotationDegree);
    }

    public void setRotationBy(float rotationDegree) {
        attacher.setRotationBy(rotationDegree);
    }

    public boolean isZoomable() {
        return attacher.isZoomable();
    }

    public void setZoomable(boolean zoomable) {
        attacher.setZoomable(zoomable);
    }

    public RectF getDisplayRect() {
        return attacher.getDisplayRect();
    }

    public void getDisplayMatrix(Matrix matrix) {
        attacher.getDisplayMatrix(matrix);
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean setDisplayMatrix(Matrix finalRectangle) {
        return attacher.setDisplayMatrix(finalRectangle);
    }

    public void getSuppMatrix(Matrix matrix) {
        attacher.getSuppMatrix(matrix);
    }

    public boolean setSuppMatrix(Matrix matrix) {
        return attacher.setDisplayMatrix(matrix);
    }

    public float getMinimumScale() {
        return attacher.getMinimumScale();
    }

    public float getMediumScale() {
        return attacher.getMediumScale();
    }

    public float getMaximumScale() {
        return attacher.getMaximumScale();
    }

    public float getScale() {
        return attacher.getScale();
    }

    public void setAllowParentInterceptOnEdge(boolean allow) {
        attacher.setAllowParentInterceptOnEdge(allow);
    }

    public void setMinimumScale(float minimumScale) {
        attacher.setMinimumScale(minimumScale);
    }

    public void setMediumScale(float mediumScale) {
        attacher.setMediumScale(mediumScale);
    }

    public void setMaximumScale(float maximumScale) {
        attacher.setMaximumScale(maximumScale);
    }

    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        attacher.setScaleLevels(minimumScale, mediumScale, maximumScale);
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener listener) {
        attacher.setOnMatrixChangeListener(listener);
    }

    public void setOnPhotoTapListener(OnPhotoTapListener listener) {
        attacher.setOnPhotoTapListener(listener);
    }

    public void setOnOutsidePhotoTapListener(OnOutsidePhotoTapListener listener) {
        attacher.setOnOutsidePhotoTapListener(listener);
    }

    public void setOnViewTapListener(OnViewTapListener listener) {
        attacher.setOnViewTapListener(listener);
    }

    public void setOnViewDragListener(OnViewDragListener listener) {
        attacher.setOnViewDragListener(listener);
    }

    public void setScale(float scale) {
        attacher.setScale(scale);
    }

    public void setScale(float scale, boolean animate) {
        attacher.setScale(scale, animate);
    }

    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        attacher.setScale(scale, focalX, focalY, animate);
    }

    public void setZoomTransitionDuration(int milliseconds) {
        attacher.setZoomTransitionDuration(milliseconds);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        attacher.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void setOnScaleChangeListener(OnScaleChangedListener onScaleChangedListener) {
        attacher.setOnScaleChangeListener(onScaleChangedListener);
    }

    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        attacher.setOnSingleFlingListener(onSingleFlingListener);
    }


    public static boolean DEBUG = false;

    private final String TAG = getClass().getSimpleName();

    /**
     * 裁剪边框的类型
     */
    public enum ClipBorderType {
        Rectangle
    }

    /**
     * 输入条件，ImageClipView根据输入条件绘制图片和裁剪边框
     */
    public static final class InputCondition {
        /**
         * 将要裁剪的原始图片
         */
        private Bitmap rawBitmap;
        private int rawBitmapWidth;
        private int rawBitmapHeight;

        /**
         * 裁剪边框的类型
         */
        private ClipBorderType clipBorderType;

        /**
         * 裁剪边框的颜色
         */
        private int clipBorderColor;

        /**
         * 裁剪边框的宽度
         */
        private int clipBorderWidth;

        private int testHeight;

        /**
         * 判定触摸裁剪边框的附加宽度（像素）
         */
        private int clipBorderAppendWidth;

        /**
         * 裁剪框的最小高度
         */
        private int clipBorderLayoutMinHeight;

        /**
         * 裁剪框的最小宽度
         */
        private int clipBorderLayoutMinWidth;

        /**
         * 是否显示宽高的值
         */
        private boolean showWidthHeightValue;

        /**
         * 输入条件的构造器
         */
        public static final class Builder {
            private final int DEFAULT_MIN_W = 100;
            private final int DEFAULT_MIN_H = 100;

            private Bitmap rawBitmap = null;
            private ClipBorderType clipBorderType = ClipBorderType.Rectangle;
            private int clipBorderColor = Color.WHITE;
            private int clipBorderWidth = 15;
            private int clipBorderAppendWidth = 20;
            private int clipBorderLayoutMinHeight = DEFAULT_MIN_H;
            private int testHeight = DEFAULT_MIN_H;
            private int clipBorderLayoutMinWidth = DEFAULT_MIN_W;
            private boolean showWidthHeightValue = true;

            public InputCondition build() {
                InputCondition condition = new InputCondition();
                if (rawBitmap != null) {
                    condition.rawBitmap = rawBitmap;
                    condition.rawBitmapHeight = rawBitmap.getHeight();
                    condition.rawBitmapWidth = rawBitmap.getWidth();
//                    condition.rawBitmapHeight = 2000;
//                    condition.rawBitmapWidth = 1000;
                }
                condition.clipBorderType = clipBorderType;
                condition.testHeight = testHeight;
                condition.clipBorderColor = clipBorderColor;
                condition.clipBorderWidth = clipBorderWidth;
                condition.clipBorderAppendWidth = clipBorderAppendWidth;
                condition.clipBorderLayoutMinHeight = Math.max(clipBorderLayoutMinHeight, DEFAULT_MIN_H);
                condition.clipBorderLayoutMinWidth = Math.max(clipBorderLayoutMinWidth, DEFAULT_MIN_W);
                condition.showWidthHeightValue = showWidthHeightValue;
                return condition;
            }

            public Builder setRawBitmap(Bitmap rawBitmap) {
                this.rawBitmap = rawBitmap;
                return this;
            }

            public Builder setClipBorderType(ClipBorderType clipBorderType) {
                this.clipBorderType = clipBorderType;
                return this;
            }

            public Builder setClipBorderColor(int clipBorderColor) {
                this.clipBorderColor = clipBorderColor;
                return this;
            }

            public Builder setClipBorderWidth(int clipBorderWidth) {
                this.clipBorderWidth = clipBorderWidth;
                return this;
            }

            public Builder setClipBorderAppendWidth(int clipBorderAppendWidth) {
                this.clipBorderAppendWidth = clipBorderAppendWidth;
                return this;
            }

            public Builder setClipBorderLayoutMinWidth(int clipBorderLayoutMinWidth) {
                this.clipBorderLayoutMinWidth = clipBorderLayoutMinWidth;
                return this;
            }

            public Builder setClipBorderLayoutMinHeight(int clipBorderLayoutMinHeight) {
                this.clipBorderLayoutMinHeight = clipBorderLayoutMinHeight;
                return this;
            }

            public Builder testHeight(int testHeight) {
                this.testHeight = testHeight;
                return this;
            }


            public Builder setShowWidthHeightValue(boolean showWidthHeightValue) {
                this.showWidthHeightValue = showWidthHeightValue;
                return this;
            }
        }
    }


    /**
     * 设置输入条件
     *
     * @param inputCondition 输入条件
     */
    public void setInputCondition(InputCondition inputCondition) {
        this.inputCondition = inputCondition;
    }

    /**
     * 根据输入条件执行绘制
     *
     * @param delay 延时绘制的时间值，单位毫秒
     */
    public void executeDrawInputCondition(long delay) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                redrawImageClipView();
            }
        };

        if (delay <= 0) {
            post(runnable);
        } else {
            postDelayed(runnable, delay);
        }
    }

    /**
     * 创建
     */
    public void onCreate(InputCondition condition, long delay) {
        setInputCondition(condition);
        executeDrawInputCondition(delay);
    }

    /**
     * 释放资源
     */
    public void onDestroy() {
        setBackgroundColor(Color.TRANSPARENT);

        if (drawBitmap != null) {
            drawBitmap.recycle();
            drawBitmap = null;
        }

        clipBorderCondition = null;
        inputCondition = null;
    }

    /**
     * 获取裁剪区域的图片
     *
     * @param inBorder 裁剪的区域是否为不包含裁剪框线的内区域
     */
    public Bitmap getClippedBitmap(boolean inBorder) {
        if (clipBorderCondition.type == ClipBorderType.Rectangle) {
            return getClippedBitmapForRect(inBorder);
        }

        return null;
    }

    /**
     * 获取矩形裁剪框区域的图片
     */
    private Bitmap getClippedBitmapForRect(boolean inBorder) {
        int left = clipBorderCondition.layoutLeft;
        int top = clipBorderCondition.layoutTop;
        int right = clipBorderCondition.layoutRight;
        int bottom = clipBorderCondition.layoutBottom;

        if (inBorder && bmScale != 1) {
            int strokeW = clipBorderCondition.width;
            left = (int) ((left + strokeW) / bmScale);
            top = (int) ((top + strokeW) / bmScale);
            right = (int) ((right - strokeW) / bmScale);
            bottom = (int) ((bottom - strokeW) / bmScale);
        }

        return Bitmap.createBitmap(inputCondition.rawBitmap, left, top, right - left, bottom - top);
    }

    //重绘
    private void redrawImageClipView() {
        ViewGroup parent = (ViewGroup) getParent();
        parentWidth = parent.getWidth();
        parentHeight = parent.getHeight();

        if (parentWidth == 0 && parentHeight == 0) {
            executeDrawInputCondition(10);
            return;
        }

        calculateSelfWidthHeightAndScaleBitmap(inputCondition);
        calculateClipperBorder(inputCondition);

        drawInputCondition = true;
        postInvalidate();
    }

    //根据图片的宽高来计算自身的宽高，以及需要绘制的新图片的宽高
    private void calculateSelfWidthHeightAndScaleBitmap(InputCondition condition) {
        int oldBmW = condition.rawBitmapWidth, oldBmH = condition.rawBitmapHeight;

        //图片宽 <= 容器宽
        if (oldBmW <= parentWidth) {
            float scale = 1F * oldBmH / parentHeight;
            selfWidth = scale <= 1 ? oldBmW : (int) (oldBmW / scale);
            selfHeight = scale <= 1 ? oldBmH : (int) (oldBmH / scale);
            bmScale = scale <= 1F ? 1F : 1F / scale;
        }
        //图片宽 > 容器宽
        else {
            float scale = 1F * parentWidth / oldBmW;
            if (DEBUG) {
                Log.d(TAG, "calculateSelfWidthHeightAndScaleBitmap: 2: scale=" + scale);
            }
            selfWidth = parentWidth;
            selfHeight = (int) (oldBmH * scale);
            bmScale = scale;
        }


        Matrix matrix = new Matrix();
        matrix.setScale(bmScale, bmScale);
        drawBitmap = Bitmap.createBitmap(condition.rawBitmap, 0, 0, condition.rawBitmapWidth, condition.rawBitmapHeight, matrix, true);

        int left = (parentWidth - selfWidth) / 2;
        int top = (parentHeight - selfHeight) / 2;
        int right = left + selfWidth;
        int bottom = top + selfHeight;
        layout(left, top, right, bottom);
    }

    //根据输入条件和自身的宽高，来计算裁剪框布局
    private void calculateClipperBorder(InputCondition condition) {
        clipBorderCondition = new ClipBorderCondition();
        clipBorderCondition.type = condition.clipBorderType;
        clipBorderCondition.color = condition.clipBorderColor;
        clipBorderCondition.width = condition.clipBorderWidth;
        clipBorderCondition.testHeight = condition.testHeight;
        clipBorderCondition.appendWidth = condition.clipBorderAppendWidth;
        clipBorderCondition.layoutLeft = 0;
        clipBorderCondition.layoutTop = (parentHeight - selfHeight) / 2;
        clipBorderCondition.layoutRight = selfWidth;
        clipBorderCondition.layoutBottom = selfHeight + (parentHeight - selfHeight) / 2;
        clipBorderCondition.minLayoutHeight = condition.clipBorderLayoutMinHeight;
        clipBorderCondition.minLayoutWidth = condition.clipBorderLayoutMinWidth;
        clipBorderCondition.showWidthHeightValue = condition.showWidthHeightValue;
    }

//    //
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if (!drawInputCondition) return;
//
//        //如果输入条件为空，则不根据输入条件进行绘制
//        if (inputCondition == null) return;
//
//        if (initFlag && !canvasDrawBitmap(canvas)) {
//            Toast.makeText(getContext(), "请正确设置裁剪图片", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (!canvasClipBorder(canvas)) {
//            Toast.makeText(getContext(), "请正确设置裁剪边框", Toast.LENGTH_SHORT).show();
//        }
//    }


    public void doDrawRect(Boolean flag, RectF rect) {
        this.flag = flag;
        this.testRect = rect;
        postInvalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (flag != null && flag) {
            canvas.drawRect(testRect, paintTest);
        } else {
        }
    }

    private boolean canvasDrawBitmap(Canvas canvas) {
        if (drawBitmap == null) return false;
        setImageBitmap(drawBitmap);
        initFlag = false;
        return true;
    }

    private boolean canvasClipBorder(Canvas canvas) {
        if (clipBorderCondition == null) return false;

        if (clipBorderCondition.type == ClipBorderType.Rectangle) {
            return drawRectangleClipBorder(canvas);
        }

        return false;
    }

    private boolean drawRectangleClipBorder(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(clipBorderCondition.color);
        paint.setStrokeWidth(clipBorderCondition.width);
        float halfWidth = 1F * clipBorderCondition.width / 2;
        int left = (int) (clipBorderCondition.layoutLeft + halfWidth);
        int top = (int) (clipBorderCondition.layoutTop + halfWidth);
        int right = (int) (clipBorderCondition.layoutRight - halfWidth);
        int bottom = (int) (clipBorderCondition.layoutBottom - halfWidth);
//        int offset = (parentHeight - bottom + top) / 2;
//        int offset = 0;
        canvas.drawRect(left, top, right, bottom, paint);

//        if (clipBorderCondition.showWidthHeightValue) {
//            paint.reset();
//            paint.setAntiAlias(true);
//            paint.setTextSize(25);
//            paint.setColor(clipBorderCondition.color);
//            String fmt = "w:%d, h:%d";
//            String text = String.format(Locale.getDefault(), fmt, right - left, bottom - top);
//            canvas.drawText(text, left + clipBorderCondition.width, top + clipBorderCondition.width + 20, paint);
//        }

        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (clipBorderCondition.type == ClipBorderType.Rectangle) {
//            Drawable imgDrawable = getDrawable();
//            if (imgDrawable != null) {
//                //获得ImageView中Image的真实宽高，
//                int dw = getDrawable().getBounds().width();
//                int dh = getDrawable().getBounds().height();
//
//                //获得ImageView中Image的变换矩阵
//                Matrix m = getImageMatrix();
//                float[] values = new float[10];
//                m.getValues(values);
//
//                //Image在绘制过程中的变换矩阵，从中获得x和y方向的缩放系数
//                float sx = values[0];
//                float sy = values[4];
//
//                //计算Image在屏幕上实际绘制的宽高
//                selfWidth = (int) (dw * sx);
//                selfHeight = (int) (dh * sy);
//            }
            return handleRectangleClipBorderOnDrawWhenDrag(event);
        }

        return super.onTouchEvent(event);
    }

    private float moveX_Rect = 0, moveY_Rect = 0;
    private boolean touchInBorderStrokeLeft_Rect = false;
    private boolean touchInBorderStrokeTop_Rect = false;
    private boolean touchInBorderStrokeRight_Rect = false;
    private boolean touchInBorderStrokeBottom_Rect = false;
    private boolean touchInBorderStrokeLeftTop_Rect = false;
    private boolean touchInBorderStrokeLeftBottom_Rect = false;
    private boolean touchInBorderStrokeRightTop_Rect = false;
    private boolean touchInBorderStrokeRightBottom_Rect = false;
    private boolean touchInBorderStroke_Rect = false;

    //处理矩形裁剪框的拖拽
    private boolean handleRectangleClipBorderOnDrawWhenDrag(MotionEvent event) {
        float downY;
        float downX;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
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

            return true;
        }
        //
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            moveX_Rect = moveY_Rect = 0;
            touchInBorderStroke_Rect = false;
            touchInBorderStrokeLeftTop_Rect = false;
            touchInBorderStrokeLeftBottom_Rect = false;
            touchInBorderStrokeRightTop_Rect = false;
            touchInBorderStrokeRightBottom_Rect = false;
            touchInBorderStrokeLeft_Rect = false;
            touchInBorderStrokeTop_Rect = false;
            touchInBorderStrokeRight_Rect = false;
            touchInBorderStrokeBottom_Rect = false;
            return true;
        }
        //
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float mX = event.getX(), mY = event.getY();
            if (DEBUG) {
                Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: MOVE: mX=" + mX + ", mY=" + mY);
            }

            //处理四边和四角拖动
            if (touchInBorderStroke_Rect) {
                if (DEBUG) {
                    Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: ACTION_MOVE: 1");
                }

                int _minW = clipBorderCondition.minLayoutWidth + clipBorderCondition.width;
                int _minH = clipBorderCondition.minLayoutHeight + clipBorderCondition.width;
                int _left = clipBorderCondition.layoutLeft;
                int _top = clipBorderCondition.layoutTop;
                int _right = clipBorderCondition.layoutRight;
                int _bottom = clipBorderCondition.layoutBottom;

                //左上角
                if (touchInBorderStrokeLeftTop_Rect && mX >= 0 && mY >= (parentHeight - selfHeight) / 2.0 && mX <= _right - _minW && mY <= _bottom - _minH) {
                    clipBorderCondition.layoutLeft = (int) mX;
                    clipBorderCondition.layoutTop = (int) mY;
                }
                //左下角
                else if (touchInBorderStrokeLeftBottom_Rect && mX >= 0 && mY <= selfHeight + (parentHeight - selfHeight) / 2.0 && mX <= _right - _minW && mY >= _top + _minH) {
                    clipBorderCondition.layoutLeft = (int) mX;
                    clipBorderCondition.layoutBottom = (int) mY;
                }
                //右上角
                else if (touchInBorderStrokeRightTop_Rect && mX <= selfWidth && mY >= (parentHeight - selfHeight) / 2.0 && mX >= _left + _minW && mY <= _bottom - _minH) {
                    clipBorderCondition.layoutRight = (int) mX;
                    clipBorderCondition.layoutTop = (int) mY;
                }
                //右下角
                else if (touchInBorderStrokeRightBottom_Rect && mX <= selfWidth && mY <= selfHeight + (parentHeight - selfHeight) / 2.0 && mX >= _left + _minW && mY >= _top + _minH) {
                    clipBorderCondition.layoutRight = (int) mX;
                    clipBorderCondition.layoutBottom = (int) mY;
                }
                //左边
                else if (touchInBorderStrokeLeft_Rect && mX >= 0 && mX <= _right - _minW) {
                    clipBorderCondition.layoutLeft = (int) mX;
                }
                //上边
                else if (touchInBorderStrokeTop_Rect && mY >= (parentHeight - selfHeight) / 2.0 && mY <= _bottom - _minH) {
                    clipBorderCondition.layoutTop = (int) mY;
                }
                //右边
                else if (touchInBorderStrokeRight_Rect && mX <= selfWidth && mX >= _left + _minW) {
                    clipBorderCondition.layoutRight = (int) mX;
                }
                //下边
                else if (touchInBorderStrokeBottom_Rect && mY <= selfHeight + (parentHeight - selfHeight) / 2.0 && mY >= _top + _minH) {
                    clipBorderCondition.layoutBottom = (int) mY;
                }

                postInvalidate();
            }
            //处理框内拖动
            else {
                if (DEBUG) {
                    Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: ACTION_MOVE: 2");
                }

                float diffX = mX - moveX_Rect, diffY = mY - moveY_Rect;
                moveX_Rect = mX;
                moveY_Rect = mY;

                float _left = clipBorderCondition.layoutLeft + diffX;
                float _top = clipBorderCondition.layoutTop + diffY;
                float _right = clipBorderCondition.layoutRight + diffX;
                float _bottom = clipBorderCondition.layoutBottom + diffY;
                int _width = clipBorderCondition.layoutRight - clipBorderCondition.layoutLeft;
                int _height = clipBorderCondition.layoutBottom - clipBorderCondition.layoutTop;

                if (DEBUG) {
                    Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: _left=" + _left + ", _top=" + _top + ", _right=" + _right + ", _bottom=" + _bottom + ", _width=" + _width + ", _height=" + _height);
                }

                if (_left >= 0 && _top >= 0 && _right <= selfWidth && _bottom <= selfHeight) {
                    if (DEBUG) {
                        Log.v(TAG, "handleRectangleClipBorderOnDrawWhenDrag: ");
                    }

                    clipBorderCondition.layoutLeft = (int) _left;
                    clipBorderCondition.layoutTop = (int) _top;
                    clipBorderCondition.layoutRight = (int) _right;
                    clipBorderCondition.layoutBottom = (int) _bottom;
                    postInvalidate();
                }
            }

            return true;
        }

        return super.onTouchEvent(event);
    }

    boolean judgeIsClipPhoto(MotionEvent event) {
        float downY;
        float downX;
        moveX_Rect = downX = event.getX();
        moveY_Rect = downY = event.getY();

        int touchWidth = clipBorderCondition.width + clipBorderCondition.appendWidth;
        int _left = clipBorderCondition.layoutLeft;
        int _top = clipBorderCondition.layoutTop;
        int _right = clipBorderCondition.layoutRight;
        int _bottom = clipBorderCondition.layoutBottom;

        touchInBorderStrokeLeft_Rect = Math.abs(downX - _left) <= touchWidth;
        touchInBorderStrokeTop_Rect = Math.abs(downY - _top) <= touchWidth;
        touchInBorderStrokeRight_Rect = Math.abs(downX - _right) <= touchWidth;
        touchInBorderStrokeBottom_Rect = Math.abs(downY - _bottom) <= touchWidth;

        touchInBorderStrokeLeftTop_Rect = touchInBorderStrokeLeft_Rect && touchInBorderStrokeTop_Rect;
        touchInBorderStrokeLeftBottom_Rect = touchInBorderStrokeLeft_Rect && touchInBorderStrokeBottom_Rect;
        touchInBorderStrokeRightTop_Rect = touchInBorderStrokeRight_Rect && touchInBorderStrokeTop_Rect;
        touchInBorderStrokeRightBottom_Rect = touchInBorderStrokeRight_Rect && touchInBorderStrokeBottom_Rect;

        touchInBorderStroke_Rect = touchInBorderStrokeLeft_Rect ||
                touchInBorderStrokeTop_Rect ||
                touchInBorderStrokeRight_Rect ||
                touchInBorderStrokeBottom_Rect /*||
                    touchInBorderStrokeLeftTop_Rect ||
                    touchInBorderStrokeLeftBottom_Rect ||
                    touchInBorderStrokeRightTop_Rect ||
                    touchInBorderStrokeRightBottom_Rect*/;

        return touchInBorderStroke_Rect;
    }
}
