package com.example.envelope.utils.extensions

import android.view.View
import android.widget.CheckedTextView

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun CheckedTextView.showCheckMark() {
    isChecked = true
}

fun CheckedTextView.hideCheckMark() {
    isChecked = false
}