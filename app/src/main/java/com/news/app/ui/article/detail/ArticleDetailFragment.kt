package com.news.app.ui.article.detail

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.news.app.R
import com.news.app.base.BaseFragment
import com.news.app.databinding.FragmentArticleDetailBinding
import com.news.app.ui.article.ArticleViewModel
import com.news.ext.fromJson
import com.news.ext.toJson
import com.news.model.Article

class ArticleDetailFragment : BaseFragment<ArticleViewModel, FragmentArticleDetailBinding>(ArticleViewModel::class.java) {

    companion object {
        private const val KEY_BUNDLE = "BUNDLE_ARTICLE"

        @JvmStatic
        fun newInstance(data: Article?): Fragment {
            val bundle = Bundle().apply {
                putString(KEY_BUNDLE, "${data?.toJson()}")
            }

            return ArticleDetailFragment().apply { arguments = bundle }
        }
    }

    private var mOptionMenu: Menu? = null

    override fun onViewCreated() {
        binding.apply {
            lifecycleOwner = this@ArticleDetailFragment
            vm = viewModel
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_article_detail
    }

    override fun setupArguments() {
        arguments?.getString(KEY_BUNDLE)?.run { binding.item = fromJson() }
    }

}