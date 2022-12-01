package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.studyapplication.R
import com.example.studyapplication.viewModel.CoroutineScopeViewModel
import kotlinx.android.synthetic.main.activity_coroutine_scope.*
import kotlinx.coroutines.*
import java.lang.Exception

class CoroutineScopeActivity : AppCompatActivity() {
    private var viewModel: CoroutineScopeViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_scope)
        viewModel = ViewModelProviders.of(this).get(CoroutineScopeViewModel::class.java)
        viewModel!!.loadData()
        bindLiveData()
    }

    private fun bindLiveData() {
        viewModel!!.liveData.observe(this, Observer {
            viewModel!!.doSth()
            println("打印主线程任务")
            coroutineScope_text.text = it
        })
    }
}