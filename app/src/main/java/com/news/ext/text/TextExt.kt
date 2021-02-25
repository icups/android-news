package com.news.ext.text

import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.news.ext.view.gone
import com.news.ext.view.visible

fun TextView.getValue(): String {
    return text.toString()
}

fun EditText.getValue(): String {
    return text.toString()
}

fun Spinner.getValue(): String {
    return selectedItem.toString()
}

fun TextView?.setData(data: String?) {
    this?.run {
        if (data.isNullOrEmpty()) gone()
        else {
            text = data
            visible(false)
        }
    }
}