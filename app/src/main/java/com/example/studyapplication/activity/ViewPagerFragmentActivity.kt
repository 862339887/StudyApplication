package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.studyapplication.R
import com.example.studyapplication.adapter.ViewPagerFragmentAdapter
import com.example.studyapplication.fragment.*
import com.example.studyapplication.view.SkipDialog
import kotlinx.android.synthetic.main.activity_view_pager_fragment.*

class ViewPagerFragmentActivity : AppCompatActivity() {
    var fragmentList = ArrayList<Fragment>()
    var curIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_fragment)
        initFragmentList()
        initViewPager()
    }


    private fun initViewPager() {
        val adapter = ViewPagerFragmentAdapter(supportFragmentManager)
        adapter.initFragmentList(fragmentList)
        view_page.adapter = adapter

        view_page.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
//                if(position!=curIndex){
//                    SkipDialog(this@ViewPagerFragmentActivity).show()
//                    view_page.setCurrentItem(curIndex,false)
//                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun initFragmentList() {
        fragmentList.add(Fragment1())
        fragmentList.add(Fragment2())
        fragmentList.add(Fragment3())
        fragmentList.add(Fragment4())
        fragmentList.add(Fragment5())
    }


    override fun onStop() {
        super.onStop()
        Log.d("zhouxin-viewpager", "onStop: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("zhouxin-viewpager", "onResume: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("zhouxin-viewpager", "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("zhouxin-viewpager", "onPause: ")
    }
}


