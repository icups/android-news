package com.news.response

import com.news.model.Article
import java.io.Serializable

data class ArticleResponse(
    val docs: List<Article> = emptyList()
) : Serializable
