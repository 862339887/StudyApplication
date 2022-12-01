package com.example.studyapplication.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_bitmap_memory_test.*

class BitmapMemoryTestActivity : AppCompatActivity() {
    private val bitmapMap = HashMap<String, Bitmap>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_memory_test)
        val options = BitmapFactory.Options()
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test_icon,options)
        val transParentBitmapTest=Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888)
        val transParentBitmapTest1=Bitmap.createBitmap(100,100,Bitmap.Config.ALPHA_8)
        bitmap.recycle()
        initView()
    }

    private fun initView() {
        increase_bitmap.setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.shoe_bag)
            bitmapMap[System.currentTimeMillis().toString() + ""] = bitmap
        }
    }
}