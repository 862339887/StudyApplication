package com.example.studyapplication.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import com.example.studyapplication.view.SkipDialog
import kotlinx.android.synthetic.main.activity_dialog_life_cycle.*

class DialogLifeCycleActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "zhouxin/test/dialog"
    }

    var flag = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_life_cycle)
        initView()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.e(TAG, "onWindowFocusChanged: $hasFocus")
    }

    private fun initView() {
        show_dialog.setOnClickListener {
            if (flag == 1) {
                val skipDialog = SkipDialog(this)
                skipDialog.show()
                flag = 0
            } else {
                Toast.makeText(this, "未失去焦点", Toast.LENGTH_SHORT).show()
            }
        }
    }


    var a = 0

    private fun test() {
        if (2 + 1.also { a = it } != 3) {
        }
    }
    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }
}