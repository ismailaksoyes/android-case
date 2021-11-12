package com.avmogame.appcent.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avmogame.appcent.data.entities.Games

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGames(games: List<Games>)

    @Query("SELECT * FROM games WHERE isFavorites = 1")
    suspend fun getFavoriteGames():List<Games>

    @Query("SELECT * FROM games WHERE name LIKE '%' || :search || '%' ")
    suspend fun getGameSearch(search:String?):List<Games>

    @Query("SELECT * FROM games WHERE gameId=:gameId")
    suspend fun getLocalGameData(gameId:Int):Games

    @Query("UPDATE games SET isFavorites = :isFav WHERE gameId=:gameId")
    suspend fun setFavoriteType(isFav:Boolean,gameId: Int)

    @Query("SELECT isFavorites FROM games WHERE gameId=:gameId")
    suspend fun getFavoriteType(gameId: Int):Boolean

    @Query("SELECT * FROM games")
    suspend fun getAllGames():List<Games>


}