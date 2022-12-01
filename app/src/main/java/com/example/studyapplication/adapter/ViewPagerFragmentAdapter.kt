package com.example.studyapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import javax.security.auth.callback.Callback

class ViewPagerFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var fragmentList = ArrayList<Fragment>()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun initFragmentList(fragmentList: ArrayList<Fragment>) {
        this.fragmentList = fragmentList
    }
}