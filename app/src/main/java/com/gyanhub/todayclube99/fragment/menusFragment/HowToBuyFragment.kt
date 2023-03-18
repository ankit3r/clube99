package com.gyanhub.todayclube99.fragment.menusFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.databinding.FragmentHowToBuyBinding

class HowToBuyFragment : Fragment() {
    private lateinit var binding: FragmentHowToBuyBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHowToBuyBinding.inflate(layoutInflater,container,false)
        (context as DetailsActivity).title = "How To Play"

        return binding.root
    }


}