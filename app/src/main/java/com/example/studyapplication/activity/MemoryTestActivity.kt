package com.example.studyapplication.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R


// 主代码


class MemoryTestActivity : AppCompatActivity() {
    private var mImageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mImageView = findViewById(R.id.image_view)

        // 请求打开图片
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            // 图片 Uri
            val imageUri = data!!.data
            // 显示在 ImageView 上
            mImageView!!.setImageURI(imageUri)
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}