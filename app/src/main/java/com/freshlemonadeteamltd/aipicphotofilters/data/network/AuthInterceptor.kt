package com.freshlemonadeteamltd.aipicphotofilters.data.network

import com.freshlemonadeteamltd.aipicphotofilters.core.DEEP_ART_KEY
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http.HttpMethod

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain
            .request()
            .url
            .newBuilder()
            .build()

        val builder = chain
            .request()
            .newBuilder()
        builder.addHeader("x-api-key", DEEP_ART_KEY)

            val request = builder
                .url(newUrl)
            .build()
        return chain.proceed(request)
    }
}