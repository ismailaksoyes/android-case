package com.avmogame.appcent.data.remote

import android.util.Log
import com.avmogame.appcent.util.Constants.API_KEY
import com.avmogame.appcent.util.Constants.BASE_URL
import okhttp3.CacheControl
import okhttp3.Cookie
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import java.util.concurrent.TimeUnit


class ApiInterceptor  @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val cacheControl = CacheControl.Builder()
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .addHeader("Accept", "application/json")
            .addHeader("Cache-Control","$cacheControl")

         val url = chain.request().url
        if (url.toString().contains(BASE_URL)){
            val newUrl = url.newBuilder().addQueryParameter("key",API_KEY).build()
            request.url(newUrl)
        }
        return chain.proceed(request.build())
    }
}