package com.example.envelope.utils.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.envelope.R
import com.google.android.material.imageview.ShapeableImageView

fun ImageView.loadUrl(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}

fun ShapeableImageView.setStepActive() {
    background = ContextCompat.getDrawable(
        this.context,
        R.drawable.progress_bg
    )
}

fun ShapeableImageView.setStepDone() {
    background = ContextCompat.getDrawable(
        context,
        R.drawable.progress_bg_done
    )
    setImageResource(R.drawable.ic_baseline_check_24)
}