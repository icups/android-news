package com.news.app.ui.article

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.app.R
import com.news.app.base.BaseActivity
import com.news.app.databinding.ActivityArticleBinding
import com.news.app.listener.LinearLoadMoreListener
import com.news.app.ui.article.ArticleViewModel.UiRequest
import com.news.app.ui.article.detail.ArticleDetailActivity
import com.news.app.ui.dialog.DialogConfirmation
import com.news.constant.Page
import com.news.ext.alert.showLongToast
import com.news.ext.alert.showSnackBar
import com.news.ext.common.exitFromApps
import com.news.ext.context.isNetworkAvailable
import com.news.ext.observer.observe
import com.news.ext.property.orZero
import com.news.model.Article
import com.news.model.State
import java.util.*

class ArticleActivity : BaseActivity<ArticleViewModel, ActivityArticleBinding>(ArticleViewModel::class.java) {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, ArticleActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var paginate: LinearLoadMoreListener
    private lateinit var adapter: ArticleAdapter

    private var onProcess: Boolean = false

    override fun onViewCreated() {
        binding.apply {
            lifecycleOwner = this@ArticleActivity
            vm = viewModel
        }
    }

    override fun layoutResources(): Int {
        return R.layout.activity_article
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.recyclerArticle.layoutManager = GridLayoutManager(this, 4)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerArticle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }

        binding.recyclerArticle.adapter = adapter
    }

    override fun initAPI() {
        viewModel.run {
            if (!isNetworkAvailable() && !appPreferences.emptyJson()) fetchArticlesFromLocalStorage()
            else fetchArticles(Page.FIRST)
        }
    }

    override fun setupAdapter() {
        adapter = ArticleAdapter(viewModel)
        binding.recyclerArticle.adapter = adapter
    }

    override fun setupObserver() {
        viewModel.run {
            observe(uiRequest) {
                when (it.first) {
                    UiRequest.TRY_AGAIN -> {
                        viewModel.fetchArticles(paginate.currentPage()); onProcess = true
                    }
                    UiRequest.DETAIL_ARTICLE -> goToDetails(it.second.article)
                    UiRequest.OFFLINE_MODE -> showSnackBar("You're in offline mode!")
                    else -> return@observe
                }
            }

            observe(articles) { result ->
                when (result) {
                    is State.Success -> assignArticles(result.data)
                    is State.Failure -> showLongToast(result.message)
                }; onProcess = false
            }
        }
    }

    override fun setupListener() {
        binding.recyclerArticle.run {
            paginate = object : LinearLoadMoreListener(layoutManager) {
                override fun isLoading(): Boolean {
                    return onProcess || !isNetworkAvailable()
                }

                override fun loadMoreItems(next: Int) {
                    viewModel.fetchArticles(next); onProcess = true
                }
            }.also { addOnScrollListener(it) }
        }
    }

    private fun assignArticles(list: List<Article>) {
        if (list.isNotEmpty()) {
            adapter.run { if (paginate.currentPage() == 1) replaceAll(list) else addAll(list) }; paginate.nextPage()
        }
    }

    private fun goToDetails(data: Article?) {
        val index = adapter.getIndexOf(data)

        val start = if ((index - 1) <= 0) 0 else (index - 1)
        val end = if ((index + 1) >= adapter.lastIndex()) 0 else (index + 1)

        val limitedList = adapter.getList().slice(start..end)
        val newIndex = limitedList.indexOf(data).orZero()

        if (limitedList.isEmpty()) return
        ArticleDetailActivity.start(this@ArticleActivity, newIndex, limitedList)
    }

    override fun onBackPressed() {
        DialogConfirmation(this@ArticleActivity) { exitFromApps() }
    }

}