package com.avmogame.appcent.di

import com.avmogame.appcent.data.api.ApiHelper
import com.avmogame.appcent.data.api.ApiHelperImpl
import com.avmogame.appcent.data.api.ApiInterceptor
import com.avmogame.appcent.data.api.ApiService
import com.avmogame.appcent.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun provideOkHttpClient(apiInterceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(apiInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Singleton
    @Provides
    fun provideApiInterceptor(apiInterceptor: ApiInterceptor): Interceptor = apiInterceptor


}