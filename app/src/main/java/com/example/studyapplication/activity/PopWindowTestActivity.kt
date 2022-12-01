package com.example.studyapplication.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_pop_window_test.*

class PopWindowTestActivity : AppCompatActivity(), View.OnTouchListener {
    private lateinit var mPopWindow: PopupWindow
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_window_test)
        initView()
        show_pop_window.setOnTouchListener(this)
        show_pop_window2.setOnTouchListener(this)
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initView() {
        show_pop_window.setOnClickListener {
            showPopupWindow()
        }

        show_pop_window2.setOnClickListener {
            Toast.makeText(this, "fdafdfa", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun showPopupWindow() {
        val popupView = LayoutInflater.from(this).inflate(R.layout.popup_window_content, null);
        mPopWindow = PopupWindow(
            popupView,
            200, 200, true
        );
        mPopWindow.isOutsideTouchable = false
        mPopWindow.isFocusable = false
        mPopWindow.isTouchable = true
        mPopWindow.contentView = popupView;
//        //设置各个控件的点击响应
//        TextView tv1 = (TextView)contentView.findViewById(R.id.pop_computer);
//        TextView tv2 = (TextView)contentView.findViewById(R.id.pop_financial);
//        TextView tv3 = (TextView)contentView.findViewById(R.id.pop_manage);
//        tv1.setOnClickListener(this);
//        tv2.setOnClickListener(this);
//        tv3.setOnClickListener(this);
        //显示PopupWindow

        mPopWindow.showAsDropDown(show_pop_window);

//        mPopWindow.showAtLocation(root, Gravity.TOP or Gravity.START, 0, 0);
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_DOWN) {

        }
        return false
    }
}