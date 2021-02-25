package com.news.app.dagger.builder

import com.news.app.ui.article.ArticleModule
import com.news.app.ui.article.detail.ArticleDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [ArticleModule::class])
    abstract fun contributeArticleDetailFragment(): ArticleDetailFragment

}