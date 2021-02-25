package com.news.ext.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.news.app.R

fun ImageView.loadImage(url: String?) {
    if (url != null && url.isEmpty()) return
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.color.colorMediumGrey)
        .error(R.drawable.img_placeholder)
        .into(this)
}