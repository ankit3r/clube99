package com.gyanhub.todayclube99.model

data class UserModel(
    val name: String,
    val email: String,
    val dob: String,
    val phno: String,
    val gender: String,
    val kyc: Boolean,

    ) {
    constructor() : this("", "", "", "", "",false)
}

