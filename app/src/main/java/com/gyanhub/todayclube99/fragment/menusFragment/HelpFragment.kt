package com.gyanhub.todayclube99.fragment.menusFragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ToggleButton
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.databinding.FragmentHelpBinding
import com.gyanhub.todayclube99.time.KeyBoardHide


class HelpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(layoutInflater, container, false)
        (context as DetailsActivity).title = "Help"

        setHelpTxt(binding.txt01, binding.txtHelp01)
        setHelpTxt(binding.txt02, binding.txtHelp02)
        setHelpTxt(binding.txt03, binding.txtHelp03)
        setHelpTxt(binding.txt04, binding.txtHelp04)
        setHelpTxt(binding.txt05, binding.txtHelp05)
        setHelpTxt(binding.txt06, binding.txtHelp06)

        binding.btnSend.setOnClickListener {
           KeyBoardHide(context as Activity).keyHide(it)
            sendMassage(binding.eTxtSub.text.toString(), binding.eTxtMass.text.toString())
        }
        binding.layoutContainer.setOnClickListener {
            KeyBoardHide(context as Activity).keyHide(it)

        }




        return binding.root
    }

    private fun setHelpTxt(view: ToggleButton, massage: View) {

        view.setButtonDrawable(R.drawable.ic_baseline_arrow_drop_down_24)
        view.setOnCheckedChangeListener { _, isChecked ->
            KeyBoardHide(context as Activity).keyHide(view)
            if (isChecked) {
                massage.visibility = View.VISIBLE
                view.setButtonDrawable(R.drawable.ic_baseline_arrow_drop_up_24)
            } else {
                massage.visibility = View.GONE
                view.setButtonDrawable(R.drawable.ic_baseline_arrow_drop_down_24)
            }
        }
    }

    private fun sendMassage(sub: String, mass: String) {
        val mailId = "ankitk31r@gmail.com"
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mailId, null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, sub)
        emailIntent.putExtra(Intent.EXTRA_TEXT, mass)
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }


}