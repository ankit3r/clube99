package com.gyanhub.todayclube99.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gyanhub.todayclube99.model.BuyTicketModel
import com.gyanhub.todayclube99.model.YourTicketModel
import com.gyanhub.todayclube99.uitle.FirebaseUtils
import com.gyanhub.todayclube99.uitle.ToastMasssage


class TicketViewModel (private val context: Context): ViewModel() {

    /********************* Buy Ticket Data ***************************/

    private val ticket: MutableLiveData<ArrayList<BuyTicketModel>?> = MutableLiveData()
    private val buyTicketData = ArrayList<BuyTicketModel>()

    val getBuyTicketData: LiveData<ArrayList<BuyTicketModel>?>
        get() = ticket

   private fun getTicket(){
       if (ticket.value != null) {
           ToastMasssage(context,"Data Clear")
           ticket.value?.clear()
       }
       FirebaseUtils().fireStoreDatabase.collection("Card")
           .get().addOnSuccessListener { document ->
               if (document != null) {
                   document.let {
                       for (snapshot in it) {
                           buyTicketData.add(snapshot.toObject(BuyTicketModel::class.java))
                       }
                   }
                   ticket.value = buyTicketData
               } else {
                   ToastMasssage(context, "No such document")
               }
           }.addOnFailureListener { exception ->
               ToastMasssage(context, "get failed with $exception")
           }


   }

    /********************* Your Ticket Data ***************************/

    private val Yticket: MutableLiveData<ArrayList<YourTicketModel>?> = MutableLiveData()
    private val YourTicketData = ArrayList<YourTicketModel>()

    val getYourTicketData: LiveData<ArrayList<YourTicketModel>?>
        get() = Yticket

    private fun addYourData() {
        YourTicketData.add(YourTicketModel("Today Club99 8 to 9 AM",""))
    }

    /*************************************************/
    init {

        getTicket()

        addYourData()
        Yticket.value = YourTicketData
    }
}