package com.gyanhub.todayclube99.adaper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.model.YourTicketModel

class YourTicketAdapter(
    private val context: Context,
    private val cardData: ArrayList<YourTicketModel>
) : RecyclerView.Adapter<YourTicketAdapter.YourViewHolder>() {
    class YourViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rc_t, parent, false)
        return YourViewHolder(view)
    }

    override fun onBindViewHolder(holder: YourViewHolder, position: Int) {
        val item = cardData[position]
    }

    override fun getItemCount(): Int {
        return cardData.size
    }

}