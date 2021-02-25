package com.news.ext.context

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.core.content.ContextCompat
import com.news.ext.property.orFalse

fun Context?.loadAnimation(@AnimRes resId: Int): Animation = AnimationUtils.loadAnimation(this, resId)

@SuppressWarnings("unused")
fun Context.getScreenWidth(): Int {
    val displayMetrics = DisplayMetrics()
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.defaultDisplay.getMetrics(displayMetrics)

    return displayMetrics.widthPixels
}

fun Context.isNetworkAvailable(): Boolean {
    val manager = ContextCompat.getSystemService(this, ConnectivityManager::class.java)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities = manager?.getNetworkCapabilities(manager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
    } else {
        return manager?.activeNetworkInfo?.isConnected.orFalse()
    }
    return false
}