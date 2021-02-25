package com.news.ext.binding

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import com.news.ext.context.getScreenWidth

fun ViewDataBinding.setupWidth(getContext: Context, w: Float) {
    if (w == 1f) return
    else root.layoutParams.run { width = (getContext.getScreenWidth() * w).toInt() }
}

fun ViewDataBinding.setupHeight(getContext: Context, w: Float) {
    if (w == 1f) return
    else root.layoutParams.run { width = (getContext.getScreenWidth() * w).toInt() }
}

fun View.initializePageWidth(getContext: Context, w: Float) {
    if (w == 1f) return
    else layoutParams.run { width = (getContext.getScreenWidth() * w).toInt() }
}