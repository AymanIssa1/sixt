package com.example.sixttask.util.extensions

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.sixttask.R

fun ImageView.loadUrl(url: String?) {
    if (!url.isNullOrBlank()) {
        if (url.endsWith("gif")) {
            Glide.with(context)
                .asGif()
                .load(url)
                .into(this)
        } else {
            Glide.with(context)
                .load(url)
                .placeholder(
                    ColorDrawable(
                        ContextCompat.getColor(
                            this.context,
                            R.color.imagePlaceholder
                        )
                    )
                )
                .into(this)
        }
    } else {
        this.setImageDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this.context,
                    R.color.imagePlaceholder
                )
            )
        )
    }
}

fun ImageView.loadUrl(url: String?, placeholderResourceId: Int?) {
    Glide.with(context)
        .load(url)
        .placeholder(placeholderResourceId ?: 0)
        .into(this)
}
