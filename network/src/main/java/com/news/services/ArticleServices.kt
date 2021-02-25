package com.news.services

import com.news.model.Article
import com.news.response.ArticleResponse
import com.news.response.PaginatedResponse
import com.news.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleServices {

    @GET("search/v2/articlesearch.json")
    suspend fun findArticles(
        @Query("api-key") apiKey: String,
        @Query("q") keyword: String,
        @Query("page") page: Int
    ): Response<ArticleResponse>

    @GET("search/v2/articlesearch.json")
    suspend fun searchArticle(
        @Query("api-key") apiKey: String,
        @Query("q") keyword: String
    ): PaginatedResponse<Article>

}