package com.avmogame.appcent.data.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor  @Inject constructor():Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .addHeader("Accept", "application/json")
            .addHeader("Content-Language","en")
            .addHeader("Cache-Control","private, no-cache, no-store, must-revalidate")
             .addHeader("Accept-Encoding","gzip, deflate")
            .build()

        return chain.proceed(request)
    }
}