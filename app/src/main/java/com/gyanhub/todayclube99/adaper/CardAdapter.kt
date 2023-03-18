package com.gyanhub.todayclube99.adaper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextClock
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.activity.MainActivity
import com.gyanhub.todayclube99.model.LocteryDataModel

class CardAdapter(
    private val context: Context,
    private val cardData: ArrayList<LocteryDataModel>
) : RecyclerView.Adapter<CardAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val prize: TextView = itemView.findViewById(R.id.txtPrize)
        val status: TextView = itemView.findViewById(R.id.txtTimer)
        val btn: Button = itemView.findViewById(R.id.btnView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_rc_item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = cardData[position]
        holder.itemTitle.text = item.ticketName
        holder.prize.text = item.prizeHop
        holder.status.text = item.status
        holder.btn.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("key","HomeDetails")
          context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cardData.size
    }

}