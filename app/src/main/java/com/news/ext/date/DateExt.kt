package com.news.ext.date

import com.news.ext.property.orOne
import com.news.ext.property.orZero
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun String.convertDatePattern(from: String, to: String): String {
    val dateFormat = SimpleDateFormat(from, Locale.getDefault())

    with(dateFormat) {
        applyPattern(from)
        val date = try {
            parse(this@convertDatePattern)
        } catch (ex: Exception) {
            return ""
        }
        applyPattern(to)
        return format(Date(date.time))
    }
}

fun String.timeAgo(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val dateString = replace("Z", "GMT+07:00")
    val millis = dateFormat.parse(dateString)?.time.orZero()
    val now = Date().time

    return (now - millis).run {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(this)
        val hours = TimeUnit.MILLISECONDS.toHours(this)
        val days = TimeUnit.MILLISECONDS.toDays(this)

        when {
            minutes < 60 -> "${minutes.orOne()}m"
            hours < 24 -> "${hours.orOne()}h"
            days < 7 -> "${days.orOne()}d"
            else -> {
                when {
                    days > 365 -> "${days.div(365).orOne()}y"
                    days > 30 -> "${days.div(30).orOne()}mo"
                    else -> "${days.div(7).orOne()}w"
                }
            }
        }
    }
}