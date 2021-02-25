package com.news.app.ui.article

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.news.app.dagger.ViewModelProviderFactory
import com.news.app.repository.ArticleRepository
import com.news.app.shared.AppPreferences
import com.news.database.ArticleDatabase
import com.news.services.ArticleServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ArticleModule {

    @Provides
    fun provideArticleServices(retrofit: Retrofit): ArticleServices {
        return retrofit.create(ArticleServices::class.java)
    }

    @Provides
    fun provideLocalDataSource(context: Context): ArticleDatabase {
        return Room.databaseBuilder(context, ArticleDatabase::class.java, "article.db").build()
    }

    @Provides
    fun provideLoginViewModel(repository: ArticleRepository, appPreferences: AppPreferences): ArticleViewModel {
        return ArticleViewModel(repository, appPreferences)
    }

    @Provides
    fun provideLoginViewModelProvider(viewModel: ArticleViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}