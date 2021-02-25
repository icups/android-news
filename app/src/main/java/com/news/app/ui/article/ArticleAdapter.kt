package com.news.app.ui.article

import com.news.app.R
import com.news.app.base.BaseSingleAdapter
import com.news.app.databinding.ItemArticleBinding
import com.news.model.Article

class ArticleAdapter(private val viewModel: ArticleViewModel) : BaseSingleAdapter<Article, ItemArticleBinding>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return try {
            mList[position].id.hashCode().toLong()
        } catch (ex: Exception) {
            0L
        }
    }

    override fun onBindBaseViewHolder(binding: ItemArticleBinding, data: Article, adapterPosition: Int) {
        binding.vm = viewModel
        binding.item = data
    }

    override fun layoutResources(): Int {
        return R.layout.item_article
    }

    override fun animated(): Boolean {
        return true
    }

}