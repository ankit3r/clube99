package com.gyanhub.todayclube99.model

data class BankAndUpiModel(
    val bankName: String,
    val acno: String,
    val ifsc: String,
    val ownerName: String,
    val upi: String
){constructor():this("","","","","")}