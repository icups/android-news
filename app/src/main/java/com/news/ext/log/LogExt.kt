package com.news.ext.log

import android.util.Log
import com.news.app.BuildConfig

fun logError(msg: String?) {
    if (BuildConfig.DEBUG) Log.e("Experiment", "OnError > $msg")
}

fun logSuccess(msg: String?) {
    if (BuildConfig.DEBUG) Log.i("Experiment", "onSuccess > $msg")
}

fun logInfo(msg: String?) {
    if (BuildConfig.DEBUG) Log.i("Experiment", "logInfo > $msg")
}