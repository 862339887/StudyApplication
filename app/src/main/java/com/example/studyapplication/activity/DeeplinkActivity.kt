package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studyapplication.R

class DeeplinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deeplink)
        val appLinkIntent = intent;
        val appLinkAction = appLinkIntent.action;
        val appLinkData = appLinkIntent.data;
    }
}