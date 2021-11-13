package com.avmogame.appcent.di

import android.content.Context
import androidx.room.Room
import com.avmogame.appcent.data.local.AppDatabase
import com.avmogame.appcent.data.repository.IGameRepository
import com.avmogame.appcent.data.repository.GameRepositoryImpl
import com.avmogame.appcent.util.Constants.GAME_DATABASE
import com.avmogame.appcent.util.Network
import com.avmogame.appcent.util.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNetwork(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        GAME_DATABASE
    ).build()

    @Singleton
    @Provides
    fun provideRoomDao(db: AppDatabase) = db.gamesDao

    @Singleton
    @Provides
    fun provideRepository(gameRepositoryImpl: GameRepositoryImpl):IGameRepository = gameRepositoryImpl
}