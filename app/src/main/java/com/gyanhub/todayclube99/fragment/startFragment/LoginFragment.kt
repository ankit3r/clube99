package com.gyanhub.todayclube99.fragment.startFragment

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.StartActivity
import com.gyanhub.todayclube99.databinding.FragmentLoginBinding
import com.gyanhub.todayclube99.time.KeyBoardHide

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)


        binding.cons.setOnClickListener {
            KeyBoardHide(context as Activity).keyHide(it)
        }
        binding.iBtnSubmit.setOnClickListener {
            KeyBoardHide(context as Activity).keyHide(it)
            if (binding.eTxtEmail.text.isEmpty() || binding.eTxtPass.text.isEmpty()) {
                Toast.makeText(context, "Enter email or password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                (context as StartActivity).login(
                    binding.eTxtEmail.text.toString(),
                    binding.eTxtPass.text.toString(),
                    binding.txtForgot
                )
            }
        }
        binding.txtForgot.setOnClickListener {
            KeyBoardHide(context as Activity).keyHide(it)
            dialogAlert()
        }
        binding.txtNewUser.setOnClickListener {
            setFragment(SignUpFragment(), "signup")
        }
        binding.iBtnGoogle.setOnClickListener {
            (context as StartActivity).signInGoogle()
        }


        return binding.root
    }

    private fun setFragment(fragment: Fragment, tag: String) {
        val fragmentTransient = parentFragmentManager.beginTransaction()
        fragmentTransient.replace(R.id.vPage2, fragment, tag)
        fragmentTransient.addToBackStack(tag)
        fragmentTransient.commit()
    }

    private fun dialogAlert() {
        val d = AlertDialog.Builder(context)
        val ediText = EditText(context)
        ediText.hint = "Enter Register Email"
        d.setTitle("Forgot Password")
        d.setView(ediText)
        ediText.setText(binding.eTxtEmail.text.toString())
        d.setPositiveButton("Yes") { dialog, _ ->
            (context as StartActivity).forgotPass(ediText.text.toString())
            dialog.cancel()
        }
        d.setNegativeButton("No") { _, _ -> }
        d.show()
    }

}