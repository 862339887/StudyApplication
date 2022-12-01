package com.example.studyapplication.activity

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.activity_time_test.*
import java.net.URLDecoder
import java.net.URLEncoder

class TimeTestActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "zhouxin--TimeTestA"
    }
    val url="aweme://echybrid?url=https%3A%2F%2Foec-api.tiktokv.com%2Fview%2Ffe_tiktok_ecommerce_voucher%2Findex.html%3FdisableBounces%3D1%26hide_source%3D1%26__status_bar%3Dtrue%26hide_nav_bar%3D1%26should_full_screen%3D1%26container_color_auto_dark%3D1%26enter_from%3Dmall%26trackParams%3D%257b%2522previous_page%2522%253a%2522mall%2522%257d"

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_test)
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun initView() {
        show_time1.setOnClickListener {
            val targetUri= Uri.parse(url)
            val targetUrl=targetUri.getQueryParameter("url")
            val newTargetUrl= URLDecoder.decode(targetUrl)
            val builder=Uri.parse(newTargetUrl).buildUpon()
            builder.appendQueryParameter("init_time", System.currentTimeMillis().toString())
            val result = targetUri.toString().replace(
                Regex("(url=[^&]*)"),
                "url=${
                    URLEncoder.encode(
                        builder.toString(),
                        "UTF-8"
                    )
                }"
            )
            print(result)
        }
        show_time2.setOnClickListener {
//            Log.d(TAG, System.currentTimeMillis().toString())
        }
        show_time3.setOnClickListener {
            val s="fdafdaf"
//            Log.d(TAG, SystemClock.elapsedRealtimeNanos().toString())
        }
    }
}