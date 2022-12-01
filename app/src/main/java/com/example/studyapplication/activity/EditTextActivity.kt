package com.example.studyapplication.activity

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_edit_text.*
import java.lang.reflect.Method

class EditTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)
        initView()
    }

    private fun initView() {
////        disableShowInput(input_edit)
//        input_edit.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                Log.d("zhouxin", "beforeTextChanged: ")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                Log.d("zhouxin", "beforeTextChanged: ")
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                Log.d("zhouxin", "beforeTextChanged: ")
//            }
//        })
//        clear_focus.setOnClickListener {
//            input_edit.clearFocus()
//        }
//
//        request_focus.setOnClickListener {
//            input_edit.requestFocus()
//        }
//
//        input_edit.addTextChangedListener(null)
//
//        show_selection.setOnClickListener {
////            hideKeyBoard(this, input_edit)
//        }
    }


//    fun hideKeyBoard(context: Context, view: View) {
//        val imm: InputMethodManager = context
//            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(view.windowToken, 0) // 强制隐藏键盘
//    }
//
//    fun disableShowInput(editText: EditText) {
//        val cls = EditText::class.java
//        var method: Method
//        try {
//            method = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.java);
//            method.isAccessible = true;
//            method.invoke(editText, false)
//        } catch (e: Exception) {
//
//        }
//        try {
//            method = cls.getMethod("setSoftInputShownOnFocus", Boolean::class.java);
//            method.isAccessible = true
//            method.invoke(editText, false);
//        } catch (e: Exception) {
//        }
//    }
}