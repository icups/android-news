package com.news.app.dagger.builder

import com.news.app.ui.article.ArticleActivity
import com.news.app.ui.article.ArticleModule
import com.news.app.ui.article.detail.ArticleDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ArticleModule::class])
    abstract fun contributeArticleActivity(): ArticleActivity

    @ContributesAndroidInjector(modules = [ArticleModule::class])
    abstract fun contributeArticleDetailActivity(): ArticleDetailActivity

}