package com.example.studyapplication.activity

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_mask.*

class MaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mask)
        initView()
    }

    private fun initView() {
        mask_iv.postDelayed({
            val bitmap = (mask_iv.drawable as BitmapDrawable).bitmap
            mask_iv.setDrawBitmap(bitmap)
        }, 300)
        mask_button.setOnClickListener {
            mask_iv.setMask(true)
        }
    }
}