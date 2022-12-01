package com.example.studyapplication.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.studyapplication.R

class EntranceListViewAdapter : BaseAdapter() {
    private var activityList = mutableListOf<Pair<String, Any>>()

    override fun getCount(): Int {
        return activityList.size
    }

    override fun getItem(position: Int): Any {
        return activityList[position].second
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.listview_item, parent, false)
        view.findViewById<TextView>(R.id.item_tv).text = activityList[position].first
        view.setOnClickListener {
            parent.context.startActivity(
                Intent(
                    parent.context,
                    activityList[position].second as Class<*>
                )
            )
        }

        return view
    }

    fun initData(activityGroup: MutableList<Pair<String, Any>>) {
        activityList = activityGroup
    }


}