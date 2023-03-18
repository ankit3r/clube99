package com.gyanhub.todayclube99.fragment.mainActivityFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {
    private lateinit var binding: FragmentWalletBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletBinding.inflate(layoutInflater, container, false)
        binding.txtRAE.setOnClickListener{
            intent("RAE")
        }
        binding.txtMyT.setOnClickListener{
            intent("History")
        }
        binding.txtAddbank.setOnClickListener {
            intent("Add Bank")
        }
        binding.txtKYC.setOnClickListener {
            intent("Kyc")
        }
        binding.txtHelp.setOnClickListener {
            intent("Help")
        }
        binding.btnWithdraw.setOnClickListener {
            intent("Withdrawal")
        }
        binding.btnAdd.setOnClickListener {
            intent("AddCash")
        }

        return binding.root
    }
    private fun intent(key: String) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("key", key)
        startActivity(intent)
    }
}