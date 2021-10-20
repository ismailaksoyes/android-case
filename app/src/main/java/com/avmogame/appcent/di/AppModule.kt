package com.avmogame.appcent.di

import android.content.Context
import com.avmogame.appcent.util.Network
import com.avmogame.appcent.util.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideNetwork(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }
}