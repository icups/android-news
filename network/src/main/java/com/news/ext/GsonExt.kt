package com.news.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T : Any> T.toJson(): String = Gson().toJson(this, T::class.java)
inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this, T::class.java)
inline fun <reified T> String?.fromJsonTyped(): T = Gson().fromJson(this, object : TypeToken<T>() {}.type)