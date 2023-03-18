package com.gyanhub.todayclube99.fragment.menusFragment


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.databinding.FragmentWithdrawalBinding
import com.gyanhub.todayclube99.time.KeyBoardHide

class WithdrawalFragment : Fragment() {
    private lateinit var binding: FragmentWithdrawalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWithdrawalBinding.inflate(layoutInflater, container, false)
        (context as DetailsActivity).title = "Withdrawal"
        (context as DetailsActivity).progre("Wait","Data Loading....")
        (context as DetailsActivity).userViewModel.getBank.observe(viewLifecycleOwner) {
                binding.card0.visibility = View.VISIBLE
                binding.card1.visibility = View.VISIBLE
                binding.txtBankName.text = it.bankName
                binding.txtAcNo.text = "A/C : ${it.acno}"
                binding.txtUpi.text = "UPI ID: ${ it.upi }"
                binding.txtUserName.text = it.ownerName
            (context as DetailsActivity).progressDialog.dismiss()
        }
        binding.txtAddBank.setOnClickListener {
            (context as DetailsActivity).whenDataFromFragment("Add Bank")
        }

        binding.layoutContainer.setOnClickListener {
            KeyBoardHide(context as Activity).keyHide(it)
        }
        return binding.root
    }

}