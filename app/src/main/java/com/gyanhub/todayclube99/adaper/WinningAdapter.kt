package com.gyanhub.todayclube99.adaper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.model.WinnerDataModel

class WinningAdapter(
    private val context: Context,
    private val cardData: ArrayList<WinnerDataModel>
) : RecyclerView.Adapter<WinningAdapter.WiningViewHolder>() {
    class WiningViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lotteryName: TextView = itemView.findViewById(R.id.txtTitleName)
        val entryFee: TextView = itemView.findViewById(R.id.txtEntryFee)
        val winningAmount: TextView = itemView.findViewById(R.id.txtWiningAmount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WiningViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wining_rc_view_item, parent, false)
        return WiningViewHolder(view)
    }

    override fun onBindViewHolder(holder: WiningViewHolder, position: Int) {
        val item = cardData[position]
        holder.lotteryName.text = item.lotteryName
        holder.entryFee.text = context.getString(R.string.entey_fee_100, item.entryFee.toString())
        holder.winningAmount.text =
            context.getString(R.string.you_won_315, item.winnAmount.toString())
    }

    override fun getItemCount(): Int {
        return cardData.size
    }
}