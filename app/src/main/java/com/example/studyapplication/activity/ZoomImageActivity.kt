package com.example.studyapplication.activity

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.studyapplication.R
import com.example.studyapplication.view.MultifunctionPhotoView
import kotlinx.android.synthetic.main.activity_zoom_image.*


class ZoomImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_image)
        val bitmap = (resources.getDrawable(R.mipmap.wallpaper) as BitmapDrawable).bitmap

        val condition: MultifunctionPhotoView.InputCondition =
            MultifunctionPhotoView.InputCondition.Builder()
                .setClipBorderType(MultifunctionPhotoView.ClipBorderType.Rectangle)
                .setClipBorderColor(Color.WHITE)
                .setClipBorderWidth(5)
                .setClipBorderAppendWidth(20)
                .setClipBorderLayoutMinWidth(200)
                .setClipBorderLayoutMinHeight(200)
//                .testHeight(clipContainer_FrameLayout_ImageClipperActivity.height)
                .setShowWidthHeightValue(true)
                .setRawBitmap(bitmap)
                .build()
        photo_view!!.onCreate(condition, 0)
        back_iv.setOnClickListener {
//            //获得ImageView中Image的变换矩阵
            val  m = photo_view.imageMatrix;
            val  values = floatArrayOf(-1F,-1F,-1F,-1F,-1F,-1F,-1F,-1F,-1F,-1F,-1F,-1F)
            m.getValues(values);
            val x1=values[0]
            val x2=values[4]
//            Log.e("zhouxin--TAG", "onCreate:${photo_view.height} ")
            val dw: Int = photo_view.drawable.bounds.width()
            val dh: Int = photo_view.drawable.bounds.height()
            Log.e("zhouxin--TAG", "onCreate:${x1} ==${x2} ")

        }
    }
}