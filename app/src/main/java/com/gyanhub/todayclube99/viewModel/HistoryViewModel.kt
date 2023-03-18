package com.gyanhub.todayclube99.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gyanhub.todayclube99.model.HistoryModel
import com.gyanhub.todayclube99.uitle.FirebaseUtils
import com.gyanhub.todayclube99.uitle.ToastMasssage


class HistoryViewModel(private val context: Context) : ViewModel() {
    private val history: MutableLiveData<ArrayList<HistoryModel>?> = MutableLiveData()
    private val historyData = ArrayList<HistoryModel>()

    val getHistoryData: LiveData<ArrayList<HistoryModel>?>
        get() = history

    private fun addData() {
        historyData.add(HistoryModel("Today Club99 8 to 9 AM", "", ""))
    }

    fun addHistory(history: HistoryModel) {
        FirebaseUtils().fireStoreDatabase.collection("users").document(FirebaseUtils().userId)
            .collection("Hoistory")
            .add(history).addOnSuccessListener {
                ToastMasssage(context,"History Data Added $it")
            }.addOnFailureListener {
                ToastMasssage(context,"Data Adding failed $it")
            }
    }

    private fun getHistoryData(){
        FirebaseUtils().fireStoreDatabase.collection("users").document(FirebaseUtils().userId)
            .collection("Hoistory").get().addOnCompleteListener{
                if (it.isSuccessful){
                    for (document in it.result) {
                        Log.d("ANKIT", document.id + " => " + document.data)
                    }
                }else{
                    ToastMasssage(context,"Something Wrong!!")
                }
            }.addOnFailureListener{
                ToastMasssage(context,"$it")
            }
    }

    init {
        addData()
        history.value = historyData
    }
}