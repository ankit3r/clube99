package com.gyanhub.todayclube99.fragment.detailsActivityFragment

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.adaper.BuyTicketAdapter
import com.gyanhub.todayclube99.adaper.HistoryAdapter
import com.gyanhub.todayclube99.databinding.FragmentHomeDetailsBinding
import com.gyanhub.todayclube99.interfaces.BuyT
import com.gyanhub.todayclube99.uitle.ToastMasssage
import com.gyanhub.todayclube99.viewModel.TicketViewModel

class HomeDetailsFragment: Fragment() , BuyT{
    private lateinit var binding: FragmentHomeDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeDetailsBinding.inflate(layoutInflater, container, false)
        (context as DetailsActivity).title = "Buy Ticket"
        (context as DetailsActivity).topBtn(0)
        setRcView(binding.rcHome)
        return binding.root
    }

    private fun setRcView(RcId: RecyclerView) {
        RcId.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        RcId.setHasFixedSize(true)

        (context as DetailsActivity).ticketViewModel.getBuyTicketData.observe(viewLifecycleOwner) {
            val adapter = it?.let { it1 -> BuyTicketAdapter(this,context as Activity, it1) }
            RcId.adapter = adapter
        }
    }

    override fun itemsClick(position: Int) {
        dialogBox(position)
            }
    private fun dialogBox(position: Int){
        val dialog = Dialog(context as Activity)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custome_dialog_layout)
        val txtpos = dialog.findViewById<TextView>(R.id.txtEntryFee)
        val close = dialog.findViewById<ImageButton>(R.id.btnClose)
        val balance = dialog.findViewById<TextView>(R.id.txtAmount)
        close.setOnClickListener{
            dialog.dismiss()
        }
//        txtpos.text= getString(R.string.entryFee,data[position].entryFee)
//        balance.text= getString(R.string.amount_added_unutilised_winnings_100,"100")
        dialog.show()
    }
}