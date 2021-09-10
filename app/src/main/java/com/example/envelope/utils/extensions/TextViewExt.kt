package com.example.envelope.utils.extensions

import android.widget.CheckedTextView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.envelope.R

fun CheckedTextView.check() {
    isChecked = true
}

fun CheckedTextView.uncheck() {
    isChecked = false
}

fun TextView.setStepActive() {
    setTextColor(
        ContextCompat.getColor(this.context, R.color.button_light_blue)
    )
}

fun CheckedTextView.setWhiteText() {
    setTextColor(
        ContextCompat.getColor(
            this.context,
            R.color.white_grey
        )
    )
}

fun CheckedTextView.setDarkText() {
    setTextColor(
        ContextCompat.getColor(
            this.context,
            R.color.black
        )
    )
}