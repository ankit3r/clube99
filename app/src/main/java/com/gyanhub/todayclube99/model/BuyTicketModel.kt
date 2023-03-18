package com.gyanhub.todayclube99.model

data class BuyTicketModel(
    val ticketName: String,
    val ticketType: String,
    val ticketNumber: String,
    val ticketFee :String,
    val ticketWinningPrize :String,

){constructor():this("","","","","") }
