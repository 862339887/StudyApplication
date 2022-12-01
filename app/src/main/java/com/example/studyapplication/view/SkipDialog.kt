package com.example.studyapplication.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_dialog_life_cycle.*
import kotlinx.android.synthetic.main.dialog_skip.*
import kotlinx.android.synthetic.main.test.*

class SkipDialog constructor(
    private val ctx: Context
) : Dialog(ctx) {

    companion object {
        private const val TAG = "zhouxin/test/dialog"
    }

    private var reasonList = ArrayList<String>()

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (ctx as DialogLifeCycleActivity).root.clearFocus()
        Log.e(TAG, "onCreate: ")
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        setCanceledOnTouchOutside(false)
        val window = window
        window?.setGravity(Gravity.TOP);
//        window!!.setBackgroundDrawable(ColorDrawable(0))
        // For ColorView in DecorView to work, FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS needs to be set
        // by default (but IME developers can opt this out later if they want a new behavior).
//        window.setLayout(MATCH_PARENT,MATCH_PARENT);
        val lp = window!!.attributes as WindowManager.LayoutParams;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 前2 个flag设置dialog 显示到状态栏    第三个设置点击dialog以外的蒙层 不抢夺焦点  响应点击事件
        lp.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN or  WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.dimAmount = 0.0f;
        window.attributes = lp;
        val view = LayoutInflater.from(context).inflate(R.layout.test, null)
        view.findViewById<TextView>(R.id.test).setOnClickListener {
            window?.decorView?.height

        }
//        initDialogContent(
//        view.findViewById<GridView>(R.id.skip_content).adapter = SkipContentAdapter(context).apply {
//            setData(reasonList)
//        }
        setContentView(view)
//
//        cancel_btn.apply {
//            setOnClickListener {
//                dismiss()
//            }
//        }
//
//        confirm_btn.apply {
//            setOnClickListener {
//                dismiss()
//            }
//        }
    }

    override fun show() {
//        (ctx as DialogLifeCycleActivity).root.clearFocus()
        super.show()
    }
    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }


    private fun initDialogContent() {
        reasonList.apply {
            add("太难了")
            add("太简单了")
            add("题目有问题")
            add("不想作答")
            add("重复做过多次")
        }
    }
}