package com.news.app.ui.article.detail

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.news.app.R
import com.news.app.base.BaseActivity
import com.news.app.databinding.ActivityArticleDetailBinding
import com.news.app.ui.article.ArticleViewModel
import com.news.app.ui.article.ArticleViewModel.UiRequest
import com.news.app.ui.article.detail.pager.ArticleDetailPagerAdapter
import com.news.ext.alert.showSnackBar
import com.news.ext.common.launchDelayedFunction
import com.news.ext.fromJsonTyped
import com.news.ext.intent.share
import com.news.ext.observer.observe
import com.news.ext.resource.getDrawableResource
import com.news.ext.toJson
import com.news.ext.view.hideWithAnimation
import com.news.model.Article

class ArticleDetailActivity : BaseActivity<ArticleViewModel, ActivityArticleDetailBinding>(ArticleViewModel::class.java) {

    companion object {
        private const val KEY_BUNDLE = "BUNDLE_ARTICLE"
        private const val KEY_POSITION = "KEY_POSITION"

        @JvmStatic
        fun start(context: Context, position: Int, list: List<Article>) {
            val starter = Intent(context, ArticleDetailActivity::class.java).putExtra(KEY_POSITION, position).putExtra(KEY_BUNDLE, list.toJson())
            context.startActivity(starter)
        }
    }

    private var mPosition: Int = 0

    private var mList: List<Article> = emptyList()
    private var mOptionMenu: Menu? = null

    override fun onViewCreated() {
        binding.apply {
            lifecycleOwner = this@ArticleDetailActivity
            vm = viewModel
            item = mList[mPosition]

            toolbar.run {
                setSupportActionBar(this)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeButtonEnabled(true)
                setNavigationOnClickListener { onBackPressed() }
            }
        }
    }

    override fun layoutResources(): Int {
        return R.layout.activity_article_detail
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_article, menu)
        mOptionMenu = menu

        setupBookmarkIcon(binding.item)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(menu: MenuItem): Boolean {
        return when (menu.itemId) {
            R.id.action_bookmark -> {
                bookmarkArticle(binding.item); true
            }
            R.id.action_share -> {
                share(binding.item?.url); true
            }
            else -> super.onOptionsItemSelected(menu)
        }
    }

    override fun setupArguments() {
        intent?.getStringExtra(KEY_BUNDLE)?.run { mList = fromJsonTyped() }
        intent?.getIntExtra(KEY_POSITION, 0)?.run { mPosition = this }
    }

    override fun setupAdapter() {
        binding.pagerArticleDetail.run {
            adapter = ArticleDetailPagerAdapter(this@ArticleDetailActivity).apply { initialize(mList) }
            setCurrentItem(mPosition, false)
        }
    }

    override fun setupListener() {
        binding.pagerArticleDetail.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.toolbar.title = mList[position].source
                binding.item = mList[position]

                setupBookmarkIcon(mList[position])
            }
        })
    }

    override fun setupObserver() {
        viewModel.run {
            observe(uiRequest) {
                when (it.first) {
                    UiRequest.CLOSE_TOOLTIP -> launchDelayedFunction {
                        binding.tooltipsSwipe.hideWithAnimation()
                        appPreferences.tooltipSwipeSeen = true
                    }
                    else -> return@observe
                }
            }
        }
    }

    private fun bookmarkArticle(data: Article?) {
        if (data == null) return
        viewModel.appPreferences.run {
            if (data.isBookmarked()) unBookmarkArticle(data)
            else {
                bookmarkArticle(data)
                showSnackBar("Article saved!")
            }
        }
        setupBookmarkIcon(data)
    }

    private fun setupBookmarkIcon(data: Article?) {
        if (data == null) return
        viewModel.appPreferences.run {
            mOptionMenu?.getItem(0)?.icon = getDrawableResource(
                if (data.isBookmarked()) R.drawable.ic_baseline_bookmarked_24 else R.drawable.ic_baseline_bookmark_24
            )
        }
    }

}