package com.gyanhub.todayclube99.model

data class YourTicketModel(
    val ticketName: String,
    val ticketBuyTime :String
    ){ constructor():this("","") }
