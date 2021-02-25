package com.news.dao

import androidx.room.*
import com.news.entity.ArticleEntity

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getAll(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(header: ArticleEntity)

    @Insert
    fun insertAll(vararg articles: ArticleEntity)

    @Update
    suspend fun update(header: ArticleEntity): Int

    @Delete
    fun delete(user: ArticleEntity)
}