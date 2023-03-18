package com.gyanhub.todayclube99.fragment.mainActivityFragments

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.MainActivity
import com.gyanhub.todayclube99.adaper.CardAdapter
import com.gyanhub.todayclube99.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setRcView(binding.rcHome)


        return binding.root
    }

    fun custome_dialog() {

        val dialog = Dialog(context as Activity)
        dialog.setContentView(R.layout.custome_edit_view)
        dialog.setCancelable(false)
        val dialogButton = dialog.findViewById<Button>(R.id.btnUpload)
        val DOB = dialog.findViewById<ImageView>(R.id.pickDOB)
        val DOBTxt = dialog.findViewById<EditText>(R.id.editTextDate)

        DOB.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(childFragmentManager, "DatePicker")

            datePicker.addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it))
                DOBTxt.setText("$date")
            }
            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(context, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG)
                    .show()
            }

            datePicker.addOnCancelListener {
                Toast.makeText(context, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }
        }

        dialogButton.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(context, "Dismissed..!!", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }


    private fun setRcView(RcId: RecyclerView) {
        RcId.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        RcId.setHasFixedSize(true)
        (context as MainActivity).cardViewModel.cardItem.observe(viewLifecycleOwner) {
            val adapter = it?.let { it1 -> CardAdapter(context as Activity, it1) }
            RcId.adapter = adapter
        }
    }

}