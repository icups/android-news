package com.news.app.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.news.constant.Page

abstract class LinearLoadMoreListener(private val layoutManager: RecyclerView.LayoutManager?) : RecyclerView.OnScrollListener() {

    var offlineMode: Boolean = false
    private var mPage = Page.FIRST

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems(mPage)
            }
        }
    }

    abstract fun isLoading(): Boolean
    abstract fun loadMoreItems(next: Int)

    fun currentPage() = mPage
    fun nextPage() = mPage++

}