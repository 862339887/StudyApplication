package com.example.studyapplication.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import com.example.studyapplication.view.MultifunctionPhotoView
import kotlinx.android.synthetic.main.activity_normal_zoom_image.*
import java.io.File
import java.io.FileOutputStream

class NormalZoomImageActivity : AppCompatActivity() {

//    val TITLE_TEXT = "TITLE_TEXT"
//    val SURE_TEXT = "SURE_TEXT"
//    val CANCEL_TEXT = "CANCEL_TEXT"
//    val IMAGE_URI = "IMAGE_URI"
//    val CLIPPED_IMG_URI_RESULT = "CLIPPED_IMG_URI_RESULT"
//    val CLIPPED_IMG_PATH_RESULT = "CLIPPED_IMG_PATH_RESULT"

    private var imageClipView: MultifunctionPhotoView? = null
    private var rawBitmap: Bitmap? = null

    override fun onBackPressed() {
        //disable
        //super.onBackPressed();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_zoom_image)

        imageClipView = findViewById(R.id.photo_view)
        back_iv.setOnClickListener {
           finish()
        }
//        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.small_example)
//        imageClipView!!.setImageBitmap(bitmap)
//        val imageUri = intent.getParcelableExtra<Uri>(IMAGE_URI)
//        val imgPath: String = LocalUri2PathUtils.getRealPathFromUri(application, imageUri)
//        Log.d(TAG, "onCreate: imgPath=$imgPath")

//        //use demo
        val condition: MultifunctionPhotoView.InputCondition =
            MultifunctionPhotoView.InputCondition.Builder()
                .setClipBorderType(MultifunctionPhotoView.ClipBorderType.Rectangle)
                .setClipBorderColor(Color.WHITE)
                .setClipBorderWidth(3)
                .setClipBorderAppendWidth(20)
                .setClipBorderLayoutMinWidth(200)
                .setClipBorderLayoutMinHeight(200)
                .setShowWidthHeightValue(true)
                .setRawBitmap(BitmapFactory.decodeResource(resources, R.mipmap.photo_example))
                .build()
        imageClipView!!.onCreate(condition, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
//        if (saveClippedImageThread != null) saveClippedImageThread!!.interrupt()
        imageClipView!!.onDestroy()
        freeBitmap(rawBitmap)
    }

    private fun freeBitmap(bitmap: Bitmap?) {
        bitmap?.recycle()
    }

    private fun executeCancel() {
        finish()
    }

//    private var saveClippedImageThread: SaveClippedImageThread? = null

//    private fun executeSure() {
//        if (saveClippedImageThread != null) {
//            Toast.makeText(application, "clipping, please waiting", Toast.LENGTH_SHORT).show()
//            return
//        }
//        saveClippedImageThread = SaveClippedImageThread()
//        saveClippedImageThread!!.context = this
//        saveClippedImageThread!!.start()
//    }

//    private class SaveClippedImageThread : Thread() {
//        private val TAG = javaClass.simpleName
//        private var bitmap: Bitmap? = null
//        var context:NormalZoomImageActivity? = null
//        private fun free() {
//            if (bitmap != null) bitmap!!.recycle()
//            context = null
//        }
//
//        override fun run() {
//            val cacheDir: File = context!!.externalCacheDir!!
//            if (cacheDir == null) {
//                Log.e(TAG, "run: ", Exception("Call Context.getExternalCacheDir() failed"))
//                free()
//                return
//            }
//            val dir = File(cacheDir.absolutePath + "/img_clip_cache")
//            if (!dir.exists()) {
//                dir.mkdirs()
//            }
//            val imgPath = dir.absolutePath + "/clipped_img_" + System.currentTimeMillis() + ".png"
//            var time = System.currentTimeMillis()
//            bitmap = context!!.imageClipView.getClippedBitmap(true)
//            Log.d(TAG, "run: clip time: " + (System.currentTimeMillis() - time))
//            try {
//                FileOutputStream(File(imgPath)).use { outs ->
//                    time = System.currentTimeMillis()
//                    bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, outs)
//                    outs.flush()
//                    Log.d(
//                        TAG,
//                        "run: save success: time=" + (System.currentTimeMillis() - time)
//                    )
//                    val intent = Intent()
//                    intent.putExtra(CLIPPED_IMG_PATH_RESULT, imgPath)
//                    intent.putExtra(CLIPPED_IMG_URI_RESULT, Uri.parse(imgPath))
//                    context.setResult(RESULT_OK, intent)
//                    context.finish()
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "run: save failed", e)
//            } finally {
//                free()
//            }
//        }
//    }
}