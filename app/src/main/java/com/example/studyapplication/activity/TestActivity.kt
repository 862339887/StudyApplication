package com.example.studyapplication.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.inputmethod.InputMethodInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity() {

    private val inputMethodChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "android.intent.action.INPUT_METHOD_CHANGED") {
                if (checkIsDefault()) {
                    finish()
                } else {
                    Toast.makeText(this@TestActivity, "请切换输入法为爱学输入法", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //输入法激活状态监听
    private var settingValueChangeContentObserver = object : ContentObserver(Handler()) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            if (checkAction()) {
                enable_aixue.isEnabled = false
                choose_aixue.isEnabled = true
                val imm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showInputMethodPicker()
            } else {
                enable_aixue.isEnabled = true
                choose_aixue.isEnabled = false
                Toast.makeText(this@TestActivity, "请勾选爱学输入法", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        registerInputMethodChanged()
        initView()
    }

    private fun initView() {
        enable_aixue.setOnClickListener {
            val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
            startActivity(intent)
        }

        choose_aixue.setOnClickListener {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showInputMethodPicker()
        }
    }

    private fun checkAction(): Boolean {
        var isInList = false
        try {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            val list: List<InputMethodInfo> = imm.enabledInputMethodList
            for (i in list.indices) {
                if (list[i].id == "com.android.inputmethod.pinyin/.PinyinIME") {
                    isInList = true
                    break
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return isInList
    }

    fun checkIsDefault(): Boolean {
        var isDefault = false
        try {
            val curInputMethodId: String = Settings.Secure.getString(
                this.contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD
            )
            if (curInputMethodId == "com.android.inputmethod.pinyin/.PinyinIME") {
                isDefault = true
            }
        } catch (e: Exception) {
            Log.e("zhouxin--checkIsDefault", "checkIsDefault: "+e.printStackTrace() )
        }
        return isDefault
    }

    private fun registerInputMethodChanged() {
        contentResolver.registerContentObserver(
            Settings.Secure.getUriFor("enabled_input_methods"),
            false,
            settingValueChangeContentObserver
        )
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.INPUT_METHOD_CHANGED")
        registerReceiver(inputMethodChangeReceiver, intentFilter)
    }

    private fun unRegisterInputMethodChanged() {

    }
}