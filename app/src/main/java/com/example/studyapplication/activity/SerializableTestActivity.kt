package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.R
import com.example.studyapplication.fragment.SerializableTestFragment
import com.example.studyapplication.model.SerializableTestModel
import kotlinx.android.synthetic.main.activity_serializable_test.*

class SerializableTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serializable_test)
        initView()

    }

    private fun initView(){
        show_dialog.setOnClickListener {
            initFragment()
        }
    }

    private fun initFragment() {

        val params=SerializableTestModel().apply {
            text1 = "22"
            text2 = "33"
        }
        val testFragment = SerializableTestFragment()
        testFragment.arguments=Bundle().apply {
            putSerializable("fddaf",params)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.test_fragment, testFragment).commit()
        supportFragmentManager.beginTransaction().show(testFragment);
    }
}