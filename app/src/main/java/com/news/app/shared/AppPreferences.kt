package com.news.app.shared

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.news.app.BuildConfig
import com.news.ext.fromJsonTyped
import com.news.ext.toJson
import com.news.model.Article

class AppPreferences(val context: Context) {

    companion object {
        const val STORED_ARTICLES = "stored_articles"
        const val BOOKMARKED_ARTICLES = "bookmarked_articles"

        const val TOOLTIP_SWIPE = "tooltip_swipe"
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    var tooltipSwipeSeen: Boolean
        get() = findPreference(TOOLTIP_SWIPE, false)
        set(value) {
            putPreference(TOOLTIP_SWIPE, value)
        }

    var articlesJson: String
        get() = findPreference(STORED_ARTICLES, "")
        set(value) {
            putPreference(STORED_ARTICLES, value)
        }

    var bookmarkedArticles: String
        get() = findPreference(BOOKMARKED_ARTICLES, "")
        set(value) {
            putPreference(BOOKMARKED_ARTICLES, value)
        }

    fun bookmarkArticle(data: Article) {
        bookmarkedArticles = if (bookmarkedArticles.isEmpty()) listOf(data).toJson()
        else {
            val list: MutableList<Article> = bookmarkedArticles.fromJsonTyped()
            list.add(data)
            list.toJson()
        }
    }

    fun unBookmarkArticle(data: Article) {
        val list: MutableList<Article> = bookmarkedArticles.fromJsonTyped()
        list.remove(data)
        bookmarkedArticles = list.toJson()
    }

    fun Article.isBookmarked(): Boolean {
        if (bookmarkedArticles.isEmpty()) return false

        val list: MutableList<Article> = bookmarkedArticles.fromJsonTyped()
        return list.contains(this)
    }

    fun emptyJson(): Boolean {
        return articlesJson.isEmpty()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> findPreference(name: String, default: T?): T = with(prefs) {
        val res: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("Type is unknown")
        }
        res as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun <T> putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }


}