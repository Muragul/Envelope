package com.example.envelope.utils.extensions

import android.widget.CheckedTextView

fun CheckedTextView.showCheckMark() {
    isChecked = true
}

fun CheckedTextView.hideCheckMark() {
    isChecked = false
}