package com.example.envelope.utils.extensions

import android.widget.CheckedTextView

fun CheckedTextView.check() {
    isChecked = true
}

fun CheckedTextView.uncheck() {
    isChecked = false
}