package com.gyanhub.todayclube99.adaper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.interfaces.BuyT
import com.gyanhub.todayclube99.model.BuyTicketModel

class BuyTicketAdapter(
    private val buyT: BuyT,
    private val context: Context,
    private val cardData: ArrayList<BuyTicketModel>
    ) : RecyclerView.Adapter<BuyTicketAdapter.BuyTicketViewHolder>() {

    class BuyTicketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: ImageButton = itemView.findViewById(R.id.btnBuy)
        val root : RelativeLayout = itemView.findViewById(R.id.Croot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyTicketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rc_t, parent, false)
        return BuyTicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuyTicketViewHolder, position: Int) {
        holder.button.setOnClickListener{
            buyT.itemsClick(position)
        }
        holder.root.setOnClickListener{
            buyT.itemsClick(position)
        }
    }

    override fun getItemCount(): Int {
        return cardData.size
    }

}