package com.example.envelope.utils.extensions

import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.envelope.R

fun EditText.showError() {
    setBackgroundResource(R.drawable.error_edit_text)
    setTextColor(
        ContextCompat.getColor(
            context,
            R.color.red
        )
    )
}