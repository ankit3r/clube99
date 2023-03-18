package com.gyanhub.todayclube99.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.snackbar.Snackbar
import com.gyanhub.todayclube99.R
import com.gyanhub.todayclube99.databinding.ActivityMainBinding
import com.gyanhub.todayclube99.factory.ViewModelFactory
import com.gyanhub.todayclube99.fragment.mainActivityFragments.HomeFragment
import com.gyanhub.todayclube99.fragment.mainActivityFragments.MenuFragment
import com.gyanhub.todayclube99.fragment.mainActivityFragments.WalletFragment
import com.gyanhub.todayclube99.fragment.mainActivityFragments.WinnersFragment
import com.gyanhub.todayclube99.uitle.NetworkConnectivity
import com.gyanhub.todayclube99.viewModel.CardViewModel
import com.gyanhub.todayclube99.viewModel.HistoryViewModel
import com.gyanhub.todayclube99.viewModel.UserViewModel
import com.gyanhub.todayclube99.viewModel.WinningViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val ID_Home = 1
    private val ID_Winner = 2
    private val ID_Wallet = 3
    private val ID_Menu = 4
    private lateinit var snackbar: Snackbar
    lateinit var progressDialog: ProgressDialog
    lateinit var cardViewModel: CardViewModel
    lateinit var winingViewModel: WinningViewModel
    lateinit var historyViewModel: HistoryViewModel
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNav()
        snackbar = Snackbar.make(binding.root, "No Internet", Snackbar.LENGTH_INDEFINITE)
        NetworkConnectivity(application).observe(this) {
            if (it) snackbar.dismiss()
            else snakbar()
        }

        cardViewModel = ViewModelProvider(this,ViewModelFactory(this,"card"))[CardViewModel::class.java]
        userViewModel = ViewModelProvider(this, ViewModelFactory(this,"user"))[UserViewModel::class.java]
        winingViewModel = ViewModelProvider(this,ViewModelFactory(this,"win"))[WinningViewModel::class.java]
        historyViewModel = ViewModelProvider(this,ViewModelFactory(this,"his"))[HistoryViewModel::class.java]

        if (supportFragmentManager.findFragmentById(R.id.vPage2) == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fMainStore, HomeFragment())
                .commit()
            title = "Home"
        }
    }

    private fun setBottomNav() {
        binding.bottomNavigation.add(
            MeowBottomNavigation.Model(
                ID_Home,
                R.drawable.ic_outline_home_24
            )
        )
        binding.bottomNavigation.add(MeowBottomNavigation.Model(ID_Winner, R.drawable.ic_winner))
        binding.bottomNavigation.add(
            MeowBottomNavigation.Model(
                ID_Wallet,
                R.drawable.ic_outline_account_balance_wallet_24
            )
        )
        binding.bottomNavigation.add(
            MeowBottomNavigation.Model(
                ID_Menu,
                R.drawable.ic_baseline_menu_24
            )
        )

        binding.bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                ID_Home -> setFragment(HomeFragment(), "home", "Home")
                ID_Winner -> setFragment(WinnersFragment(), "winner", "Result")
                ID_Wallet -> setFragment(WalletFragment(), "wallet", "Wallet")
                ID_Menu -> setFragment(MenuFragment(), "menu", "Menu")
            }
        }
        binding.bottomNavigation.show(ID_Home, true)
    }

    private fun setFragment(fragment: Fragment, tag: String, text: String) {
        title = text
        val fragmentTransient = supportFragmentManager.beginTransaction()
        supportFragmentManager.popBackStack()
        fragmentTransient.replace(R.id.fMainStore, fragment, tag).addToBackStack(tag)
        fragmentTransient.commit()
    }

    override fun onBackPressed() {
        title = "Home"
        binding.bottomNavigation.show(ID_Home, true)
        super.onBackPressed()
    }

    private fun snakbar() {
        snackbar.setAction("RETRY") {
            NetworkConnectivity(application).observe(this) {
                if (it) snackbar.dismiss()
                else snakbar()
            }
        }
        snackbar.setActionTextColor(Color.RED)
        val sbView = snackbar.getView()
        val textView =
            sbView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.YELLOW)
        snackbar.show()
    }

    private fun print(massage: String) {
        Toast.makeText(this, massage, Toast.LENGTH_LONG).show()
    }
    fun progre(title: String, mass: String) {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle(title)
        progressDialog.setMessage(mass)
        progressDialog.show()
    }
}