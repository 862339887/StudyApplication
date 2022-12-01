package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.studyapplication.R
import com.example.studyapplication.viewModel.ViewModelLiveData
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModelLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        viewModel = ViewModelProviders.of(this).get(ViewModelLiveData::class.java)
        viewModel.liveData.observe(this, Observer {
            Log.e("LiveDataActivity", "onCreate: ")
        })
//        root_cl.postDelayed({
//            viewModel.liveData.observe(this, Observer {
//                Log.e("LiveDataActivity", "onCreate: ")
//            })
//        }, 3000)
    }
}