package com.example.envelope.utils.extensions

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.envelope.R

fun LinearLayout.setWhiteBackground() {
    setBackgroundColor(
        ContextCompat.getColor(
            context,
            R.color.white_grey
        )
    )
}

fun LinearLayout.setDarkBackground() {
    setBackgroundColor(
        ContextCompat.getColor(
            context,
            R.color.main_page_background_dark
        )
    )
}

fun ViewGroup.startDistributionAnimation() {
    startAnimation(
        AnimationUtils.loadAnimation(
            context,
            R.anim.slide_up
        )
    )
}