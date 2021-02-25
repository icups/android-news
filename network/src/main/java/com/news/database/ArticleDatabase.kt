package com.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.news.dao.ArticleDao
import com.news.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}