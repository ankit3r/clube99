package com.gyanhub.todayclube99.model

data class HistoryModel(
    val time: String,
    val status : String,
    val amount: String,

){
    constructor():this("","","")
}