package com.gyanhub.todayclube99.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.databinding.ActivityDetailsBinding
import com.gyanhub.todayclube99.factory.ViewModelFactory
import com.gyanhub.todayclube99.fragment.detailsActivityFragment.HomeDetailsFragment
import com.gyanhub.todayclube99.fragment.detailsActivityFragment.YourTicketFragment
import com.gyanhub.todayclube99.fragment.menusFragment.*
import com.gyanhub.todayclube99.viewModel.*

@Suppress("DUPLICATE_LABEL_IN_WHEN")
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    lateinit var fragmentTransient: FragmentTransaction
    private var key: String = ""
    lateinit var progressDialog: ProgressDialog
    lateinit var cardViewModel: CardViewModel
    lateinit var winingViewModel: WinningViewModel
    lateinit var historyViewModel: HistoryViewModel
    lateinit var userViewModel: UserViewModel
    lateinit var ticketViewModel: TicketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolBar()

        binding.btnBuyT.setOnClickListener {
            selectBtn(0)
            whenDataFromFragment("HomeDetails")
        }
        binding.btnYourT.setOnClickListener {
            selectBtn(1)
            whenDataFromFragment("Your Ticket")
        }
        cardViewModel = ViewModelProvider(this,ViewModelFactory(this,"card"))[CardViewModel::class.java]
        userViewModel = ViewModelProvider(this, ViewModelFactory(this,"user"))[UserViewModel::class.java]
        winingViewModel = ViewModelProvider(this,ViewModelFactory(this,"win"))[WinningViewModel::class.java]
        historyViewModel = ViewModelProvider(this,ViewModelFactory(this,"his"))[HistoryViewModel::class.java]
        ticketViewModel = ViewModelProvider(this,ViewModelFactory(this,"tick"))[TicketViewModel::class.java]

        key = intent.getStringExtra("key").toString()
        whenData(key)
    }

    private fun setUpToolBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun whenData(key: String) {
        when (key) {
            "HomeDetails" -> setFm(HomeDetailsFragment(), key, "Buy Ticket")
            "Profile" -> setFm(ProfileFragment(), key, "Profile")
            "Kyc" -> setFm(KycFragment(), key, "KYC")
            "Withdrawal" -> setFm(WithdrawalFragment(), key, "Withdrawal")
            "Add Bank" -> setFm(AddBankFragment(), key, "Add Bank")
            "History" -> setFm(HistoryFragment(), key, "History")
            "Help" -> setFm(HelpFragment(), key, "Help")
            "HTB" -> setFm(HowToBuyFragment(), key, "How To Play")
            "RAE" -> setFm(ReferAndEarnFragment(), key, "Refer And Earn")
            "AddCash" -> setFm(AddCashFragment(), key, "Add Cash")
            "Your Ticket" -> setFm(YourTicketFragment(), key, "Your Ticket")
        }

    }

    fun whenDataFromFragment(key: String) {
        when (key) {
            "HomeDetails" -> setFmOnFm(HomeDetailsFragment(), key, "Buy Ticket")
            "Profile" -> setFmOnFm(ProfileFragment(), key, "Profile")
            "Kyc" -> setFmOnFm(KycFragment(), key, "KYC")
            "Withdrawal" -> setFmOnFm(WithdrawalFragment(), key, "Withdrawal")
            "Add Bank" -> setFmOnFm(AddBankFragment(), key, "Add Bank")
            "History" -> setFmOnFm(HistoryFragment(), key, "History")
            "Your Ticket" -> setFmOnFm(YourTicketFragment(), key, "Your Ticket")
        }

    }

    private fun setFm(fmId: Fragment, key: String, text: String) {
        title = text
        fragmentTransient = supportFragmentManager.beginTransaction()
        fragmentTransient.add(R.id.fDetailStore, fmId, key)
        fragmentTransient.commit()
    }

    private fun setFmOnFm(fmId: Fragment, key: String, text: String) {
        title = text
        fragmentTransient = supportFragmentManager.beginTransaction()
        supportFragmentManager.popBackStack()
        fragmentTransient.replace(R.id.fDetailStore, fmId, key)
        fragmentTransient.addToBackStack(key).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun topBtn(num: Int) {
        binding.linearLayout.visibility = View.VISIBLE
        selectBtn(num)
    }

    private fun selectBtn(num: Int) {
        when (num) {
            0 -> {
                binding.btnYourT.setBackgroundResource(0)
                binding.btnBuyT.setBackgroundResource(R.drawable.box_for_editext)
            }
            1 -> {
                binding.btnBuyT.setBackgroundResource(0)
                binding.btnYourT.setBackgroundResource(R.drawable.box_for_editext)
            }
        }
    }
     fun progre(title: String, mass: String) {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle(title)
        progressDialog.setMessage(mass)
        progressDialog.show()
    }

}