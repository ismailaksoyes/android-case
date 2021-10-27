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


class TokenInterceptor  @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()

         val url = chain.request().url
        if (url.toString().contains(BASE_URL)){
            val newUrl = url.newBuilder().addQueryParameter("key",API_KEY).build()
            request.url(newUrl)
        }
        return chain.proceed(request.build())
    }
}