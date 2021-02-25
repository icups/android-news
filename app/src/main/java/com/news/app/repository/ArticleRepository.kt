package com.news.app.repository

import com.news.app.BuildConfig
import com.news.constant.Page
import com.news.database.ArticleDatabase
import com.news.entity.ArticleEntity
import com.news.model.Article
import com.news.response.ArticleResponse
import com.news.response.Response
import com.news.services.ArticleServices
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val remoteDataSource: ArticleServices, private val localDataSource: ArticleDatabase) {

    suspend fun findArticles(page: Int): Response<ArticleResponse> {
        return remoteDataSource.findArticles(BuildConfig.API_KEY, Page.DEFAULT_KEYWORD, page)
    }

    suspend fun getArticlesFromLocalStorage(): List<Article> {
        return localDataSource.articleDao().getAll().map { it.toArticle() }.toList()
    }

    suspend fun saveArticles(list: List<Article>) {
        return localDataSource.articleDao().insertAll()
    }

    fun ArticleEntity.toArticle(): Article {
        return Article()
    }

}