package com.gyanhub.todayclube99.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gyanhub.todayclube99.model.UserModel
import com.gyanhub.todayclube99.model.WinnerDataModel
import com.gyanhub.todayclube99.uitle.FirebaseUtils
import com.gyanhub.todayclube99.uitle.ToastMasssage

class WinningViewModel(private val context: Context): ViewModel() {
    private val wItems: MutableLiveData<ArrayList<WinnerDataModel>?> = MutableLiveData()
    private val wItemData = ArrayList<WinnerDataModel>()

    val winCardItem: LiveData<ArrayList<WinnerDataModel>?>
        get() = wItems

    private fun addData() {
        wItemData.add(WinnerDataModel("Today Club99 8 to 9 AM",100,200))
    }
//    private fun getData(){
//        FirebaseUtils().fireStoreDatabase.collection("users")
//            .document(FirebaseUtils().userId).get().addOnSuccessListener { document ->
//                if (document != null) {
//                    wItems.value = document.toObject(WinnerDataModel::class.java) ?: WinnerDataModel()
//                }
//            }.addOnFailureListener { exception ->
//                ToastMasssage(context, "get failed with $exception")
//            }
//    }
    init {
        addData()
        wItems.value = wItemData
    }
}