package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view: View? = this.currentFocus
    view?.let { inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0) }
}

fun Activity.isKeyboardOpen(): Boolean {
    val view: View = findViewById(android.R.id.content)
    val rect = Rect()
    view.getWindowVisibleDisplayFrame(rect)
    val windowHeight = this.window.decorView.height
    val minDp =  100 * ((this.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat())
    return (windowHeight - rect.bottom > minDp)
}

fun Activity.isKeyboardClosed(): Boolean{
    return !isKeyboardOpen()
}