package com.news.ext.property

import androidx.fragment.app.Fragment

val Fragment?.get: Fragment get() = this ?: Fragment()

val String?.get: String get() = this ?: ""

val Boolean?.get: Boolean get() = this ?: false

val Int?.get: Int get() = this ?: 0

val Float?.get: Float get() = this ?: 0f

fun Boolean?.orTrue(): Boolean = this ?: true
fun Boolean?.orFalse(): Boolean = this ?: false

fun Double?.orZero(): Double = this ?: 0.0
fun Double?.orOne(): Double = this ?: 1.0

fun Int?.orZero(): Int = this ?: 0
fun Int?.orOne(): Int = this ?: 1

fun Long?.orZero(): Long = this ?: 0L
fun Long?.orOne(): Long = this ?: 1L

fun Float?.orZero(): Float = this ?: 0f
fun Float?.orOne(): Float = this ?: 1f