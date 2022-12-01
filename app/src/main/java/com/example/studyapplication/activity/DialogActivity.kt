package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.studyapplication.R
import com.example.studyapplication.view.SkipDialog
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        dialog_invoke.setOnClickListener {
            val skipDialog=SkipDialog(this)
            skipDialog.show()
            val resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
            val height = resources.getDimensionPixelSize(resourceId);
            Log.v("dbw", "Navi height:" + height);
        }
    }
}