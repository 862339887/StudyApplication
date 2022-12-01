package com.example.studyapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecyclerViewTestAdapter constructor(
    var context: Context
) :
    RecyclerView.Adapter<RecyclerViewTestAdapter.WordViewHolder>() {
    private var wordGroupData: ArrayList<String>? = null

    companion object {
        private const val TAG = "RecyclerViewTestAdapter"
    }

    fun setWordWritingData(wordGroupData: ArrayList<String>) {
        this.wordGroupData = wordGroupData
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        wordGroupData!!.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordViewHolder {
        return WordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_test_item, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        Log.e(TAG, "getItemViewType1111111:")
        return position
    }

    override fun getItemCount(): Int {
        return wordGroupData!!.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val data = wordGroupData!![position]
        holder.text.text=data
//        holder.text.text = data
//        holder.text.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                delay(2000)
//                holder.text.text = (holder.text.text.toString().toInt() * 2).toString()
//            }
//        }
//        holder.root.setOnClickListener {
//            Log.e(TAG, "onBindViewHolder: 22222222" )
//        }
//        if (position == 0) holder.root.performClick()
    }


    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text = view.findViewById<TextView>(R.id.test_tv)
//        val root = view.findViewById<LinearLayout>(R.id.root)
    }
}