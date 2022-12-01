package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.R

class LeetCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leet_code)
        getReg("https://oec-api.tiktokv.com/view/fe_tiktok_ecommerce_voucher/index.html?disableBounces=1&hide_source=1&__status_bar=true&hide_nav_bar=1&should_full_screen=1&container_color_auto_dark=1&enter_from=mall&trackParams=%7b%22previous_page%22%3a%22mall%22%7d")
    }

    private fun getReg(url: String) {
        val pattern = "view/.*/index\\.html".toRegex()
        pattern.find(url)!!.value
        if (pattern.containsMatchIn(url)) {
            println("zhouxin--test-true")
        }
    }
}



