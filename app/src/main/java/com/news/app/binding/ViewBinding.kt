package com.news.app.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.news.ext.property.orFalse
import com.news.ext.view.backgroundColor
import com.news.ext.view.gone
import com.news.ext.view.invisible
import com.news.ext.view.visible

object ViewBinding {

    @JvmStatic
    @BindingAdapter("backgroundColor")
    fun setBackgroundColor(view: View, hexCode: String? = "#ffffff") {
        hexCode?.let { view.backgroundColor(it) }
    }

    @JvmStatic
    @BindingAdapter("visibility")
    fun setVisibility(view: View, visibility: Int) {
        when (visibility) {
            View.VISIBLE -> view.visible()
            View.GONE -> view.gone()
            View.INVISIBLE -> view.invisible()
        }
    }

    @JvmStatic
    @BindingAdapter("refreshing")
    fun setRefreshing(view: SwipeRefreshLayout, refreshing: Boolean?) {
        view.isRefreshing = refreshing.orFalse()
    }

}