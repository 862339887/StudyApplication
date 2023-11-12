package com.example.studyapplication.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.studyapplication.R
import com.example.studyapplication.others.Rotate3dAnimation
import kotlinx.android.synthetic.main.activity_test3_drotate.*


class Test3DRotateActivity : Activity() {
    /** Called when the activity is first created.  */
    private val TAG = "Test3DRotateActivity"
//    private var startNext: Test3DRotateActivity.StartNextRotate? = null
    private var testView:View?=null
    private var rotation: Animation? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test3_drotate)
        start.setOnClickListener {
            val centerX = image_icon.width/2f
            val centerY = image_icon.height/2f
            startRotation(0f, 120f,centerX,centerY, image_icon, false, 2000) {
//                image_logo.visibility = View.VISIBLE
//                startRotation(90f, 0f, centerX,centerY,image_logo, false, 2000)
            }
//            startAnimationTest()
//            startRotation(0f, 90f, image_logo, false, 2000)

            //上层
//            startRotation(0f, -90f, image_above,true,2500)
            //底层
//            startRotation(0f, -180f, image_below, false,5000)

        }
        stop.setOnClickListener {
            image_icon.animation = null
            image_icon.visibility = View.VISIBLE
//            image_icon.animation?.cancel()
//            Log.e("zhouxin-tabicon1",image_icon.animation.toString())
//            image_icon.clearAnimation()
//            Log.e("zhouxin-tabicon1",image_icon.animation.toString())

//            image_icon.animation?.toString()?.let { it1 -> Log.e("zhouxin-tabicon2", it1) }

            // TODO Auto-generated method stub
//            startRotation(-90f, 0f, image_below, false, 2000)

//            image_below.clearAnimation()
        }

        cancel.setOnClickListener {
            rotation?.cancel()
        }
        clear.setOnClickListener {
            image_icon?.clearAnimation()
        }

        set_null.setOnClickListener {
            image_icon?.animation = null
        }
    }

    private fun startRotation(
        start: Float,
        end: Float,
        centerX:Float,
        centerY:Float,
        rotatedImageView: View,
        needDismiss: Boolean,
        duration: Long,
        callBack: (()->Unit)?=null
    ) {
        // 计算中心点

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        //final Rotate3dAnimation rotation =new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        //Z轴的缩放为0
        rotation = Rotate3dAnimation(start, end, centerX, centerY, 0f, false)
        rotation?.duration = duration
        rotation?.fillAfter = true
        //rotation.setInterpolator(new AccelerateInterpolator());
        //匀速旋转
        rotation?.interpolator = LinearInterpolator()
        //设置监听
//        startNext = StartNextRotate()
        rotation?.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                Log.e("zhouxin-start", needDismiss.toString())
            }

            override fun onAnimationEnd(animation: Animation?) {
//                image_below.visibility = View.VISIBLE
                Log.e("zhouxin-end", needDismiss.toString())
                callBack?.invoke()
            }

            override fun onAnimationRepeat(animation: Animation?) {
//                TODO("Not yet implemented")
            }

        })
        rotatedImageView.startAnimation(rotation)
    }

//    private inner class StartNextRotate : AnimationListener {
//        override fun onAnimationEnd(animation: Animation) {
//            // TODO Auto-generated method stub
////            image.startAnimation(rotation)
//        }
//
//        override fun onAnimationRepeat(animation: Animation) {
//            // TODO Auto-generated method stub
//        }
//
//        override fun onAnimationStart(animation: Animation) {
//            // TODO Auto-generated method stub
//        }
//    }


    private fun startAnimationTest(){
        val animationSet = AnimationSet(true)
        val translateAnimation = TranslateAnimation( //X轴初始位置
            Animation.RELATIVE_TO_SELF, 0.0f,  //X轴移动的结束位置
            Animation.RELATIVE_TO_SELF, 0.5f,  //y轴开始位置
            Animation.RELATIVE_TO_SELF, 0.0f,  //y轴移动后的结束位置
            Animation.RELATIVE_TO_SELF, 1.5f
        )

        //3秒完成动画

        //3秒完成动画
        translateAnimation.duration = 2000
        //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
        //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
        animationSet.fillAfter = true
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        animationSet.addAnimation(translateAnimation)

        //启动动画
        //启动动画
        image_icon.startAnimation(animationSet)
    }
}



