package com.enigma.remote.retrofit

import okhttp3.Interceptor
import okhttp3.Response

private const val TOKEN_KEY = "X-RapidAPI-Key"
private const val TOKEN_VALUE = "75ba1eae7bmshaf47280f1bd4278p1bd43cjsn61f84622791e"

class RequestInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().addHeader(
            TOKEN_KEY,
            TOKEN_VALUE
        ).build())
    }
}