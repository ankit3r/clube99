package com.gyanhub.todayclube99.fragment.menusFragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.activity.MainActivity
import com.gyanhub.todayclube99.databinding.FragmentAddBankBinding
import com.gyanhub.todayclube99.model.BankAndUpiModel
import com.gyanhub.todayclube99.model.UserModel
import com.gyanhub.todayclube99.time.KeyBoardHide
import com.gyanhub.todayclube99.uitle.FirebaseUtils
import com.gyanhub.todayclube99.uitle.ToastMasssage

class AddBankFragment : Fragment() {
    private lateinit var binding: FragmentAddBankBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBankBinding.inflate(layoutInflater, container, false)
        (context as DetailsActivity).title = "Add Bank"
        (context as DetailsActivity).progre("Wait", "Data Loading....")
        binding.layoutContainer.setOnClickListener {
            KeyBoardHide(context as Activity).keyHide(it)
        }

        (context as DetailsActivity).userViewModel.getBank.observe(viewLifecycleOwner) {

            binding.eTxtBankName.setText(it.bankName)
            binding.eTxtAcNo.setText(it.acno)
            binding.eTxtAcNoCon.setText(it.acno)
            binding.eTxtIFSC.setText(it.ifsc)
            binding.eTxtOwnerName.setText(it.ownerName)
            binding.eTxtUpi.setText(it.upi)
            (context as DetailsActivity).progressDialog.dismiss()
        }
        (context as DetailsActivity).userViewModel.getUser.observe(viewLifecycleOwner) { user ->
            binding.eTxtOwnerName.setText(user.name)
            (context as DetailsActivity).progressDialog.dismiss()
        }
        binding.btnVerify.setOnClickListener {
            if (binding.eTxtAcNo.text.toString() == binding.eTxtAcNoCon.text.toString()) {
                addBank(
                    BankAndUpiModel(
                        binding.eTxtBankName.text.toString(),
                        binding.eTxtAcNo.text.toString(),
                        binding.eTxtIFSC.text.toString(),
                        binding.eTxtOwnerName.text.toString(),
                        binding.eTxtUpi.text.toString()
                    )
                )
            } else {
                binding.eTxtAcNoCon.error = "No Match Account Number"
            }
        }
        binding.btnUpiVerify.setOnClickListener {
            if (binding.eTxtUpi.text.isNotEmpty()) {
                addBank(
                    BankAndUpiModel(
                        binding.eTxtBankName.text.toString(),
                        binding.eTxtAcNo.text.toString(),
                        binding.eTxtIFSC.text.toString(),
                        binding.eTxtOwnerName.text.toString(),
                        binding.eTxtUpi.text.toString()
                    )
                )
            }
        }

        return binding.root
    }

    private fun addBank(bankAndUpiModel: BankAndUpiModel) {
        (context as DetailsActivity).progre("Wait", "Data Uploading....")
        FirebaseUtils().fireStoreDatabase.collection("users")
            .document(FirebaseUtils().userId).collection("BankAndUpi")
            .document("Bank${FirebaseUtils().userId}").set(bankAndUpiModel)
            .addOnSuccessListener {
                (context as DetailsActivity).progressDialog.dismiss()
                ToastMasssage(context as Activity, "Added Successfully")
            }.addOnFailureListener {
                (context as DetailsActivity).progressDialog.dismiss()
                ToastMasssage(context as Activity, "Data Adding Failed. Try Again ")
            }
    }


}