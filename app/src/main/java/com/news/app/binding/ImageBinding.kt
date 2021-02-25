package com.news.app.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.news.ext.image.loadImage

object ImageBinding {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImageFromUrl(view: ImageView?, url: String?) {
        view?.loadImage(url)
    }

}