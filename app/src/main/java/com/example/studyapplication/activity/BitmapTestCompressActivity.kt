package com.example.studyapplication.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_bitmap_test.*

class BitmapTestCompressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_test)
        initView()
    }

    private fun initView() {
        test_bitmap_btn_8888.setOnClickListener {
            print("a")
            testRgbBitmap(rgb_8888_bitmap,Bitmap.Config.ARGB_8888)
        }
        test_bitmap_btn_565.setOnClickListener {
            testRgbBitmap(rgb_565_bitmap,Bitmap.Config.RGB_565)
        }
    }

    private fun testRgbBitmap(view: ImageView, bitmapConfig: Bitmap.Config){
        val options = BitmapFactory.Options()
        options.inPreferredConfig = bitmapConfig
        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.english_word_app_ic,options)
        view.setImageBitmap(bitmap)
    }

//    private fun testCompressBitmap(){
//        val bmp = BitmapFactory.decodeResource(resources, R.drawable.twitter_icon)
//        val saveDir: String? = FileUtil.getCacheFilePathDir(this)
//        val savePath1 = "compress_image1_" + ".jpeg"
//        val savePath2 = "compress_image2_" + ".jpeg"
//        FileUtil.saveBitmapToSDTest(bmp, saveDir, savePath1, 100)
//        FileUtil.saveBitmapToSDTest(bmp, saveDir, savePath2, 10)
//        val bmpPath1 =
//            "/storage/emulated/0/Android/data/com.example.studyapplication/cache/share_content_cache/$savePath1"
//        val bmpPath2 =
//            "/storage/emulated/0/Android/data/com.example.studyapplication/cache/share_content_cache/$savePath2"
//        setCompressBitmap(bmpPath1, no_compress_bitmap)
//        setCompressBitmap(bmpPath2, compress_bitmap)
//    }
//
//    private fun setCompressBitmap(path: String, view: ImageView) {
//        val fis = FileInputStream(path)
//        val bitmap = BitmapFactory.decodeStream(fis)
//        view.setImageBitmap(bitmap)
//    }
}