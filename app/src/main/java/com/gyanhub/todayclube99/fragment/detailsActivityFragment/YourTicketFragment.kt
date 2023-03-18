package com.gyanhub.todayclube99.fragment.detailsActivityFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.adaper.BuyTicketAdapter
import com.gyanhub.todayclube99.adaper.YourTicketAdapter
import com.gyanhub.todayclube99.databinding.FragmentYourTicketBinding
import com.gyanhub.todayclube99.viewModel.TicketViewModel

class YourTicketFragment : Fragment() {

    private lateinit var binding: FragmentYourTicketBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYourTicketBinding.inflate(layoutInflater, container, false)
        (context as DetailsActivity).title = "Your Ticket"
        (context as DetailsActivity).topBtn(1)

        setRcView(binding.recyclerView)
        return binding.root
    }

    private fun setRcView(RcId: RecyclerView) {
        RcId.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        RcId.setHasFixedSize(true)

        (context as DetailsActivity).ticketViewModel.getYourTicketData.observe(viewLifecycleOwner) {
            val adapter = it?.let { it1 -> YourTicketAdapter(context as Activity, it1) }
            RcId.adapter = adapter
        }
    }

}