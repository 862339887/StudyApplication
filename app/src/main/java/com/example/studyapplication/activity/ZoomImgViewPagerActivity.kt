package com.example.studyapplication.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.studyapplication.R
import com.example.studyapplication.view.ZoomImageView


class ZoomImgViewPagerActivity : AppCompatActivity() {
    //记录上一个位置
    private var currentPosition = 0
    private var views = ArrayList<ZoomImageView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_img_view_pager)
        val vp = findViewById<View>(R.id.vp) as ViewPager
        for (i in 0..3) {
            views.add(ZoomImageView(this))
        }
        vp.adapter = MyPagerAdapter(applicationContext, views)
        vp.pageMargin = 10
        vp.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                Log.d("fiend", "position:$position")
                val zoomImageView = views[currentPosition]
                //复原
                zoomImageView.restore()
                currentPosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    internal class MyPagerAdapter(
        private val mContext: Context,
        private val views: ArrayList<ZoomImageView>
    ) :
        PagerAdapter() {
        override fun getCount(): Int {
            return views.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = views[position]
            imageView.setImageResource(R.mipmap.photo_example)
            container.addView(imageView)
            if (position == 0) {
                imageView.setInterruptLeft(false)
            }
            if (position == count - 1) {
                imageView.setInterruptRight(false)
            }
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}