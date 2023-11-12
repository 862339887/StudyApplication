package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.studyapplication.R
import com.example.studyapplication.`interface`.ApiClient
import com.example.studyapplication.viewModel.CoroutineScopeViewModel
import com.example.studyapplication.viewModel.NetTestViewModel

class NetApiTestActivity : AppCompatActivity() {

    private lateinit var viewModel: NetTestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_api_test)
        viewModel = ViewModelProvider(this).get(NetTestViewModel::class.java)
        viewModel.fetchUser(20)
    }


}