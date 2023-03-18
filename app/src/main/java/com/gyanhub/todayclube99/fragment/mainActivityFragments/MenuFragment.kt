package com.gyanhub.todayclube99.fragment.mainActivityFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.activity.DetailsActivity
import com.gyanhub.todayclube99.activity.MainActivity
import com.gyanhub.todayclube99.activity.StartActivity
import com.gyanhub.todayclube99.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)

        // call requestIdToken as follows
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(context as Activity, gso)


        val massa = "Sending text content\n" +
                "The most straightforward and common use of the Android Sharesheet is to send text " +
                "content from one activity to another. For example, most browsers can share the URL " +
                "of the currently-displayed page as text with another app. This is useful for sharing " +
                "an article or website with friends via email or social networking. Here's an example " +
                "of how to do this: \n" +
                "https://developer.android.com/training/sharing/send"
        binding.txtProfile.setOnClickListener {
            intent("Profile")
        }
        binding.txtYourTicket.setOnClickListener {
            intent("Your Ticket")
        }
        binding.txtKyc.setOnClickListener {
            intent("Kyc")
        }
        binding.txtWithdrawal.setOnClickListener {
            intent("Withdrawal")
        }
        binding.txtAddBank.setOnClickListener {
            intent("Add Bank")
        }
        binding.txtHistory.setOnClickListener {
            intent("History")
        }
        binding.txtHelp.setOnClickListener {
            intent("Help")
        }
        binding.txtHTB.setOnClickListener {
            intent("HTB")
        }
        binding.btnLogOut.setOnClickListener {
            Firebase.auth.signOut()
            mGoogleSignInClient.signOut().addOnCompleteListener {
                startActivity(Intent(context as Activity, StartActivity::class.java))
                (context as MainActivity).finish()
            }
        }

        binding.txtShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, massa)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        return binding.root
    }

    private fun intent(key: String) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("key", key)
        startActivity(intent)
    }
}