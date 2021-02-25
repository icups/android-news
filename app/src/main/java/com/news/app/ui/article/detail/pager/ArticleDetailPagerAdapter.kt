package com.news.app.ui.article.detail.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.news.app.base.BasePagerAdapter
import com.news.app.ui.article.detail.ArticleDetailFragment
import com.news.model.Article

class ArticleDetailPagerAdapter(val context: FragmentActivity) : BasePagerAdapter<Article>(context) {

    override fun initiateFragment(data: Article): Fragment {
        return ArticleDetailFragment.newInstance(data)
    }

}