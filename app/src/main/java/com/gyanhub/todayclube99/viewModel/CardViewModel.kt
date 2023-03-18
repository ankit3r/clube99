package com.gyanhub.todayclube99.viewModel


import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.toObject
import com.gyanhub.todayclube99.model.LocteryDataModel
import com.gyanhub.todayclube99.model.UserModel
import com.gyanhub.todayclube99.uitle.FirebaseUtils
import com.gyanhub.todayclube99.uitle.ToastMasssage

class CardViewModel(private val context: Context) : ViewModel() {
    private val cItems: MutableLiveData<ArrayList<LocteryDataModel>?> = MutableLiveData()
    private val cItemData = ArrayList<LocteryDataModel>()
    val cardItem: LiveData<ArrayList<LocteryDataModel>?>
        get() = cItems


    private fun getCardData() {
        if (cItems.value != null) {
            ToastMasssage(context,"Data Clear")
            cItems.value?.clear()
        }
        FirebaseUtils().fireStoreDatabase.collection("Card")
            .get().addOnSuccessListener { document ->
                if (document != null) {
                    document.let {
                        for (snapshot in it) {
                            cItemData.add(snapshot.toObject(LocteryDataModel::class.java))
                        }
                    }
                    cItems.value = cItemData
                    ToastMasssage(context, "Data loaded")
                } else {
                    ToastMasssage(context, "No such document")
                }
            }.addOnFailureListener { exception ->
                ToastMasssage(context, "get failed with $exception")
            }


    }

    init {
        getCardData()
    }
}