package com.example.studyapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studyapplication.R
import com.example.studyapplication.adapter.RecyclerViewTestAdapter
import kotlinx.android.synthetic.main.activity_recycler_view_test.*

class RecyclerViewTestActivity : AppCompatActivity() {
    private var adapter:RecyclerViewTestAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_test)
        initView()
    }

    private fun initView() {
        val dataList = ArrayList<String>()
        for (i in 1..50) {
            dataList.add(i.toString())
            val layoutManager = LinearLayoutManager(this).apply {
                orientation=LinearLayoutManager.HORIZONTAL
            }
            adapter = RecyclerViewTestAdapter(this)
            test_recycler.adapter = adapter

            test_recycler.layoutManager = layoutManager
            adapter!!.setWordWritingData(dataList)
        }

        remove_item.setOnClickListener {
            adapter!!.removeItem(1)
        }
    }


}