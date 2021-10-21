package com.avmogame.appcent.data.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor  @Inject constructor(private val API_KEY:String):Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .addHeader("Accept", "application/json")
            .build()
        request.url.newBuilder().addQueryParameter("key",API_KEY)

        return chain.proceed(request)
    }
}