package com.example.studyapplication.activity

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_bitmap_recycle.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


class BitmapRecycleActivity : AppCompatActivity() {
    private var bitmap: Bitmap? = null
    private var number = 0

    private val images = arrayOf(R.drawable.english_word_icon, R.drawable.app_interaction_icon)

    companion object {
        private const val TAG = "BitmapRecycleActivity"
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_recycle)
        val options = BitmapFactory.Options()
        options.inMutable = true
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.english_word_icon, options)
        image_test1.setImageBitmap(bitmap)
        image_recycle.setOnClickListener {
            bitmap!!.recycle()
//            number++
//            val options = BitmapFactory.Options()
////            options.inMutable=true
////            options.inBitmap=bitmap
//            bitmap = BitmapFactory.decodeResource(resources, images[number % 2], options)
//            val arrays= IntArray(bitmap.width*bitmap.height)
//            bitmap.getPixels(arrays,0,bitmap.width,0,0,bitmap.width,bitmap.height)
//            reuseBitmap(true)
//            decodeLocalPicture()
//            testCompressBitmap(bitmap, 100)
//            testCompressBitmap(bitmap, 80)
//            testCompressBitmap(bitmap, 60)
//            testCompressBitmap(bitmap, 40)
//            testCompressBitmap(bitmap, 20)
//            decodeBitmapFromResource(1)
//            decodeBitmapFromResource(2)
//            decodeBitmapFromResource(3)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bitmap?.recycle()
        bitmap=null
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun reuseBitmap(reuseFlag: Boolean) {
        val options = BitmapFactory.Options()
        if (reuseFlag){
            options.inMutable=true
            options.inBitmap = bitmap
        }
            BitmapFactory.decodeResource(resources, R.drawable.app_interaction_icon, options)
    }

    @SuppressLint("LongLogTag")
    private fun decodeLocalPicture() {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ALPHA_8
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test_fdfd, options)
        Log.e(
            "${TAG}_ARGB_ALPHA",
            "width:" + bitmap.width + "height:" + bitmap.height + "size:" + bitmap.byteCount
        )
        val options1 = BitmapFactory.Options()
        options1.inPreferredConfig = Bitmap.Config.RGB_565
        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.test_fdfd, options1)
        Log.e(
            "${TAG}_ARGB_565",
            "width:" + bitmap1.width + "height:" + bitmap1.height + "size:" + bitmap1.byteCount
        )
        val options2 = BitmapFactory.Options()
        options2.inPreferredConfig = Bitmap.Config.ARGB_8888
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.test_fdfd, options2)
        Log.e(
            "${TAG}_ARGB_8888",
            "width:" + bitmap2.width + "height:" + bitmap2.height + "size:" + bitmap2.byteCount
        )
    }

    @SuppressLint("LongLogTag")
    private fun testCompressBitmap(bitmap: Bitmap, quality: Int) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
        val byte = baos.toByteArray()
        val ins = ByteArrayInputStream(byte)
        val bitmap = BitmapFactory.decodeStream(ins)
        ins.close()
        baos.close()
        Log.e("${TAG}_memory_${quality}", "size:" + bitmap.byteCount)
        Log.e("${TAG}_file_${quality}", "quality=$quality,byte-size=${byte.size}")
    }

    @SuppressLint("LongLogTag")
    fun decodeBitmapFromResource(inSampleSize: Int) {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.shoe_bag, options)
        //获取采样率
        options.inSampleSize = inSampleSize
        options.inJustDecodeBounds = false
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.shoe_bag, options)
        Log.e(
            "${TAG}_inSampleSize:${inSampleSize}",
            "width:" + bitmap.width + "height:" + bitmap.height + "size:" + bitmap.byteCount
        )
    }

}