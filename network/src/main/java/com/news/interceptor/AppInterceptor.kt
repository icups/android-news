package com.news.interceptor

import com.news.exception.NetworkException
import com.news.ext.fromJson
import com.news.response.ErrorResponse
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AppInterceptor(private var accessToken: String? = null) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val newRequest = request.newBuilder().apply {
            addHeader("Accept", "application/json")
            accessToken?.run { addHeader("Authorization", "Bearer $this") }
        }.build()

        val response = chain.proceed(newRequest)

        if (response.isSuccessful) return response
        else throw NetworkException(getErrorFrom(response))
    }

    private fun getErrorFrom(response: Response): ErrorResponse? {
        return response.body?.string()?.fromJson()
    }

}