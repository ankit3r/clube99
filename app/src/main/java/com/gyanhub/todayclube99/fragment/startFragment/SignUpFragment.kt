package com.gyanhub.todayclube99.fragment.startFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyanhub.todayclube99.activity.StartActivity
import com.gyanhub.todayclube99.databinding.FragmentSignUpBinding
import com.gyanhub.todayclube99.time.KeyBoardHide

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        binding.imgBtnBack.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.coc.setOnClickListener{
            KeyBoardHide(context as Activity).keyHide(it)
        }

        binding.btnRegister.setOnClickListener {
            KeyBoardHide(context as Activity).keyHide(it)
            if (checkEntry()) {
                (context as StartActivity).signUpUser(
                    binding.eTxtEmail.text.toString(),
                    binding.eTxtPass02.text.toString()
                )
            }else{
                return@setOnClickListener
            }

        }

        binding.iBtnGoogle3.setOnClickListener {
            (context as StartActivity).signInGoogle()
        }

        return binding.root
    }

    private fun checkEntry(): Boolean {
        if (binding.eTxtName.text.toString() == "") {
            binding.eTxtName.error = "Pleas enter Name"
            return false
        } else if (binding.eTxtPhNo.text.toString() == "") {
            binding.eTxtPhNo.error = "Pleas enter Whatsapp no"
            return false
        } else if (binding.eTxtEmail.text.toString() == "") {
            binding.eTxtEmail.error = "Pleas enter Email"
            return false
        } else if (binding.eTxtpass01.text.toString() == "") {
            binding.eTxtpass01.error = "Pleas enter Strong Password"
            return false
        } else if (binding.eTxtPass02.text.toString() != binding.eTxtpass01.text.toString()) {
            binding.eTxtPass02.error = "Pleas enter Same Password"
            return false
        } else if (!(binding.checkBox.isChecked)) {
            binding.checkBox.error = "Pleas Accept It"
            return false
        } else return true
    }

}


