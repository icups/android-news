package com.news.ext.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.app.ShareCompat

fun Activity.share(url: String? = null) {
    if (url.isNullOrEmpty()) return
    ShareCompat.IntentBuilder
        .from(this)
        .setType("text/plain")
        .setChooserTitle("Share Link")
        .setText(url)
        .startChooser()
}

fun Activity.goPlayStore(packageName: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
}