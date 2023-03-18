package com.gyanhub.todayclube99.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gyanhub.todayclube99.viewModel.*

class ViewModelFactory(private val context: Context, private val model: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (model) {
            "user" -> UserViewModel(context) as T
            "card" -> CardViewModel(context) as T
            "his" -> HistoryViewModel(context) as T
            "win" -> WinningViewModel(context) as T
            "tick" -> TicketViewModel(context) as T
            else -> UserViewModel(context) as T
        }
    }
}