package com.gyanhub.todayclube99.model

data class BalanceModel(
    val withdrawBalance:String,
    val bonusBalance:String,
    val addBalance:String
){constructor():this("","","")}
