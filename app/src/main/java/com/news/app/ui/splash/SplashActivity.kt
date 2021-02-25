package com.news.app.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.news.app.ui.article.ArticleActivity
import com.news.ext.common.launchDelayedFunction

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeSplash()
    }

    private fun initializeSplash() {
        launchDelayedFunction {
            ArticleActivity.start(this@SplashActivity)
            finish()
        }
    }

}