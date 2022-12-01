package com.example.studyapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R

class Fragment5 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_page_fragment5, container, false)
    }

    private var isVisibleToUser: Boolean = false

    override fun onResume() {
        super.onResume()
        Log.e("Fragment", "Fragment5可见")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser

        if (isVisibleToUser) {
            Log.e("Fragment", "Fragment2可见")
        } else {
            Log.e("Fragment", "Fragment2不可见")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("onDestroyView", "Fragment5不可见")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "Fragment5销毁了")
    }
}