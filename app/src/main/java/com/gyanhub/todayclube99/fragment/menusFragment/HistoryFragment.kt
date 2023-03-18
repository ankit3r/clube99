package com.gyanhub.todayclube99.fragment.menusFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.adaper.HistoryAdapter
import com.gyanhub.todayclube99.databinding.FragmentHistoryBinding
import com.gyanhub.todayclube99.viewModel.HistoryViewModel

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        (context as DetailsActivity).title = "History"
        setRcView(binding.rcHistory)

        return binding.root
    }

    private fun setRcView(RcId: RecyclerView) {
        RcId.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        RcId.setHasFixedSize(true)

        (context as DetailsActivity).historyViewModel.getHistoryData.observe(viewLifecycleOwner) {
            val adapter = it?.let { it1 -> HistoryAdapter(context as Activity, it1) }
            RcId.adapter = adapter
        }
    }

}