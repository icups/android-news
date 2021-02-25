package com.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.news.app.repository.ArticleRepository
import com.news.app.ui.article.ArticleViewModel
import com.news.constant.Page
import com.news.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ArticleViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: ArticleRepository

    lateinit var viewModel: ArticleViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = ArticleViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchTopHeadlineSuccessfully() {
        runBlockingTest {
            viewModel.fetchArticles(Page.FIRST)
            viewModel.articles.observeForever {
                when (it) {
                    is State.Success -> assertThat(it.data).isNotEmpty()
                    else -> return@observeForever
                }
            }
        }
    }

    @Test
    fun emptyTopHeadlines() {
        runBlockingTest {
            viewModel.fetchArticles(Page.FIRST)
            viewModel.articles.observeForever {
                when (it) {
                    is State.Success -> assertThat(it.data).isEmpty()
                    else -> return@observeForever
                }
            }
        }
    }

    @Test
    fun fetchTopHeadlineFailure() {
        runBlockingTest {
            viewModel.fetchArticles(Page.FIRST)
            viewModel.articles.observeForever {
                when (it) {
                    is State.Failure -> assertThat(it.message).isNotEmpty()
                    else -> return@observeForever
                }
            }
        }
    }

}