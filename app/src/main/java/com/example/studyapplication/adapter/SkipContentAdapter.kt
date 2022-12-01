package com.example.studyapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.studyapplication.R
import kotlinx.android.synthetic.main.skip_dialog_item.view.*

class SkipContentAdapter(private val context: Context) : BaseAdapter() {
    private var clickPosition = -1
    private var reasonList: List<String>? = null
    private var is_Selected: Boolean = false
    private var clickItemView: View? = null
    fun setData(reasonList: List<String>) {
        this.reasonList = reasonList
    }

    override fun getCount(): Int {
        return if (reasonList == null) {
            0
        } else {
            reasonList!!.size
        }
    }

    override fun getItem(position: Int): Any {
        return reasonList!![position]
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        return if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.skip_dialog_item, parent, false)
                .apply {
                    setOnClickListener {
                        if (!is_Selected) {
                            item_check.setBackgroundResource(R.drawable.click_selected)
                            is_Selected = true
                            clickItemView = this
                            Log.e("SkipContentAdapter", "getView: $clickPosition")
                        } else {
                            if (position == clickPosition) {
                                item_check.setBackgroundResource(R.drawable.default_slected)
                                is_Selected = false
                            } else {
                                clickItemView!!.item_check.setBackgroundResource(R.drawable.default_slected)
                                item_check.setBackgroundResource(R.drawable.click_selected)
                                clickItemView = this
                            }
                        }
                        clickPosition = position
                    }

                }
            view.item_content.text = reasonList!![position]
            view
        } else {
            convertView
        }
    }
}