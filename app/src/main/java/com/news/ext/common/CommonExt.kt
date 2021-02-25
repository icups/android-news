package com.news.ext.common

import android.os.Handler

fun launchDelayedFunction(timeMillis: Long = 500, action: () -> Unit) {
    Handler().postDelayed({ action() }, timeMillis)
}

fun exitFromApps() {
    android.os.Process.killProcess(android.os.Process.myPid())
}