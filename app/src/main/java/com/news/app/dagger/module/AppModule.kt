package com.news.app.dagger.module

import android.content.Context
import com.news.app.MyApplication
import com.news.app.shared.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: MyApplication): Context {
        return application
    }

    @Singleton
    @Provides
    fun providePreferences(context: Context): AppPreferences {
        return AppPreferences(context)
    }

}