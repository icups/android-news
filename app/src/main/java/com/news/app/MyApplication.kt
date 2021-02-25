package com.news.app

import android.content.ContextWrapper
import com.news.app.dagger.component.DaggerAppComponent
import com.pixplicity.easyprefs.library.Prefs
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        initializePreferences()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this@MyApplication)
    }

    private fun initializePreferences() {
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

}