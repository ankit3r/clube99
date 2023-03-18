package com.gyanhub.todayclube99.model

data class LocteryDataModel(
    val ticketName: String,
    val status: String,
    val prizeHop: String
) {
    constructor() : this("", "", "")
}
