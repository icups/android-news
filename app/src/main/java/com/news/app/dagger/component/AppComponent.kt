package com.news.app.dagger.component

import com.news.app.MyApplication
import com.news.app.dagger.builder.ActivityBuilder
import com.news.app.dagger.builder.FragmentBuilder
import com.news.app.dagger.module.AppModule
import com.news.app.dagger.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class,
        ActivityBuilder::class, FragmentBuilder::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()

}