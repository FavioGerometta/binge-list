package com.xing.binge.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Activity.hideSoftKeyboard() {
    val focusedView = currentFocus
    focusedView?.let { view ->
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Context.longToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.shortToast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()