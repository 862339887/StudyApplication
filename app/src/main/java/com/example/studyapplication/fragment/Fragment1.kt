package com.example.studyapplication.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.studyapplication.R

//setUserVisibleHint调用在onResume之前调用，且调用两次；时序为setUserVisibleHint（false）、setUserVisibleHint（true）、onResume
//
class Fragment1 : Fragment() {
    private var isVisibleToUser: Boolean = false
    private var isViewInitiated: Boolean = false
    private lateinit var viewModel: ViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_page_fragment1, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "Fragment1可见")
    }

    override fun onResume() {
        super.onResume()
        Log.e("onResume", "Fragment1")

//        if(isVisibleToUser){
//            Log.e("onResume", "Fragment1可见")
//        }else{
//            Log.e("onResume", "Fragment1不可见")
//        }
    }

    override fun onPause() {
        super.onPause()
        Log.e("onPause", "Fragment1可见")
    }

    override fun onStop() {
        super.onStop()
        Log.e("onStop", "Fragment1可见")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("onDestroyView", "Fragment1不可见")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "Fragment1销毁了")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("onDetach", "Fragment1可见")

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("setUserVisibleHint", "Fragment1")
//        this.isVisibleToUser = isVisibleToUser
//        if(isVisibleToUser){
//            Log.e("isVisibleToUser", "Fragment1可见")
//        }else{
//            Log.e("isVisibleToUser", "Fragment1不可见")
//        }
    }
}