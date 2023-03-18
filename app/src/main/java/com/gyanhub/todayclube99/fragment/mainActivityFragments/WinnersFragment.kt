package com.gyanhub.todayclube99.fragment.mainActivityFragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyanhub.todayclube99.activity.MainActivity
import com.gyanhub.todayclube99.adaper.WinningAdapter
import com.gyanhub.todayclube99.databinding.FragmentWinnersBinding

class WinnersFragment : Fragment() {
    private lateinit var binding: FragmentWinnersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWinnersBinding.inflate(layoutInflater, container, false)
        setRcView(binding.rcWinn)

        return binding.root
    }

    private fun setRcView(RcId: RecyclerView) {
        RcId.setHasFixedSize(true)
        RcId.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        (context as MainActivity).winingViewModel.winCardItem.observe(viewLifecycleOwner) {
            if (it?.size!! == 0) {
                binding.txtNoDataMassage.visibility = View.VISIBLE
            } else {
                binding.txtNoDataMassage.visibility = View.GONE
                val adapter = WinningAdapter(context as Activity, it)
                RcId.adapter = adapter
            }
        }

    }



}