package com.example.studyapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R

class Fragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_page_fragment2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("onActivityCreated", "Fragment2可见")

    }
    private var isVisibleToUser: Boolean = false
    override fun onResume() {
        super.onResume()
        if(isVisibleToUser){
            Log.e("onResume", "Fragment2可见")
        }else{
            Log.e("onResume", "Fragment2不可见")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "Fragment2销毁了")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("onDestroyView", "Fragment2不可见")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if(isVisibleToUser){
            Log.e("isVisibleToUser", "Fragment2可见")
        }else{
            Log.e("isVisibleToUser", "Fragment2不可见")
        }
    }
}