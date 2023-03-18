package com.gyanhub.todayclube99.viewModel

import android.app.Activity
import android.content.Context

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gyanhub.todayclube99.model.BalanceModel
import com.gyanhub.todayclube99.model.BankAndUpiModel
import com.gyanhub.todayclube99.model.UserModel
import com.gyanhub.todayclube99.uitle.FirebaseUtils
import com.gyanhub.todayclube99.uitle.ToastMasssage

class UserViewModel(private val context: Context) : ViewModel() {
    //********************** user ***********************************//
    private val user = MutableLiveData<UserModel>()
    val getUser: LiveData<UserModel>
        get() = user

    //********************** bank ****************************//
    private val bank = MutableLiveData<BankAndUpiModel>()
    val getBank: LiveData<BankAndUpiModel>
        get() = bank

    //********************** user added ***********************************//
    fun addUserData(user: UserModel) {
        FirebaseUtils().fireStoreDatabase.collection("users").document(FirebaseUtils().userId)
            .set(user).addOnSuccessListener {
                ToastMasssage(context, "Data Added $it")
            }.addOnFailureListener {
                ToastMasssage(context, "Data Adding failed $it")
            }
    }

    //********************** user data get ***********************************//
    private fun getUserData() {
        FirebaseUtils().fireStoreDatabase.collection("users")
            .document(FirebaseUtils().userId).get().addOnSuccessListener { document ->
                if (document != null) {
                    user.value = document.toObject(UserModel::class.java) ?: UserModel()
                }
            }.addOnFailureListener { exception ->
                ToastMasssage(context, "get failed with $exception")
            }
    }

    //********************** user bank get ***********************************//
    private fun getBankData() {
        FirebaseUtils().fireStoreDatabase.collection("users")
            .document(FirebaseUtils().userId).collection("BankAndUpi")
            .document("Bank${FirebaseUtils().userId}").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    bank.value = document.toObject(BankAndUpiModel::class.java) ?: BankAndUpiModel()
                }
            }.addOnFailureListener { exception ->
                ToastMasssage(context as Activity, "get failed with $exception")
            }
    }

    //********************************* balance added ************************\\
    fun addbalance(balanceModel: BalanceModel){
        FirebaseUtils().fireStoreDatabase.collection("users")
            .document(FirebaseUtils().userId).collection("Balance")
            .document("Balance${FirebaseUtils().userId}").
            set(balanceModel).addOnSuccessListener {
            ToastMasssage(context, "Data Added")
        }.addOnFailureListener {
            ToastMasssage(context, "Data Adding failed")
        }
    }

    //****************************** get Balance ***************************//
    private val balance = MutableLiveData<BalanceModel>()
    val getCurrentBalance: LiveData<BalanceModel>
        get() = balance

    private fun getBalanceData() {
        FirebaseUtils().fireStoreDatabase.collection("users")
            .document(FirebaseUtils().userId).collection("Balance")
            .document("Balance${FirebaseUtils().userId}")
            .get().addOnSuccessListener { document ->
                if (document != null) {
                    balance.value = document.toObject(BalanceModel::class.java) ?: BalanceModel()
                }
            }.addOnFailureListener { exception ->
                ToastMasssage(context, "get failed with $exception")
            }
    }
    //********************** Init ***********************************//
    init {
        getUserData()
        getBankData()
        getBalanceData()
    }
}