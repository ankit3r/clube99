package com.gyanhub.todayclube99.fragment.menusFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        (context as DetailsActivity).title = "Profile"
        (context as DetailsActivity).progre("Waiting","Data Loading...")
        (context as DetailsActivity).userViewModel.getUser.observe(viewLifecycleOwner) { user ->
           binding.txtName.text = "Name: ${ user.name }"
           binding.txtEmail.text = "Email: ${ user.email }"
           binding.txtDOB.text = "DOB: ${ user.dob }"
           binding.txtGender.text = "Gender: ${ user.gender }"
           binding.txtPhNo.text = "Phone No: ${ user.phno }"
            (context as DetailsActivity).progressDialog.dismiss()
        }

        return binding.root
    }


}