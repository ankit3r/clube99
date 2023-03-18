package com.gyanhub.todayclube99.time

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


class KeyBoardHide(private val activity: Activity) {
    fun keyHide(view: View) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}