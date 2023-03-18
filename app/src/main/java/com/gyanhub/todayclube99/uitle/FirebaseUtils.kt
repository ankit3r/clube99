package com.gyanhub.todayclube99.uitle


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    val auth =  FirebaseAuth.getInstance()
   val userId =  auth.currentUser?.uid.toString()
}