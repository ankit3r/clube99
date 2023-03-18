package com.gyanhub.todayclube99.fragment.menusFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.databinding.FragmentReferAndEarnBinding

class ReferAndEarnFragment : Fragment() {
    private lateinit var binding: FragmentReferAndEarnBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReferAndEarnBinding.inflate(layoutInflater, container, false)
        (context as DetailsActivity).title = "Refer And Earn"

        return binding.root
    }
}