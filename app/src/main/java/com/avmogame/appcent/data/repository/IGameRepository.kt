package com.avmogame.appcent.data.repository

import com.avmogame.appcent.data.entities.Games
import com.avmogame.appcent.data.entities.ResponseGame
import com.avmogame.appcent.data.entities.ResponseGames
import com.avmogame.appcent.util.Resource

interface IGameRepository {

    suspend fun getGameDetails(gameId: Int): Resource<ResponseGame>
    suspend fun getGameList(page: Int?): Resource<ResponseGames>
    suspend fun setLocalGameData(games: List<Games>)
    suspend fun getLocalFavoritesGames(): List<Games>
    suspend fun searchGame(search: String): List<Games>
    suspend fun getLocalGameData(gameId: Int): Games
    suspend fun setFavoritesType(isFav: Boolean, gameId: Int)
    suspend fun getFavoriteType(gameId: Int): Boolean
}