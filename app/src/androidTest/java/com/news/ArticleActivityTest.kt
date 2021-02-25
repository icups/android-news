package com.news

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.news.app.ui.article.ArticleActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleActivityTest {

    lateinit var scenario: ActivityScenario<ArticleActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(ArticleActivity::class.java)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun articleShowed() {
        scenario.onActivity {
            assert(it.binding.recyclerArticle.adapter?.itemCount ?: 0 > 0)
        }
    }

}