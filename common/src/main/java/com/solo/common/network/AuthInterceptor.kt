package com.solo.common.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/octet-stream")
            .addHeader("X-RapidAPI-Key", "9faef52244mshef69988e3edd7adp1de84cjsn75e6743e52eb")
            .addHeader("X-RapidAPI-Host", "advanced-movie-search.p.rapidapi.com")
            .build()
        return chain.proceed(request)
    }
}