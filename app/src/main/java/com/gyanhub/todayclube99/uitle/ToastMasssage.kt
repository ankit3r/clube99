package com.gyanhub.todayclube99.uitle

import android.content.Context
import android.widget.Toast

class ToastMasssage(context: Context,massage: String) {
    init {
        Toast.makeText(context, massage, Toast.LENGTH_SHORT).show()
    }
}