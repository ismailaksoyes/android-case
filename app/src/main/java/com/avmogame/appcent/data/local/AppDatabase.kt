package com.avmogame.appcent.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.avmogame.appcent.data.entities.Games

@Database(entities = [Games::class],version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract val gamesDao:GamesDao
}