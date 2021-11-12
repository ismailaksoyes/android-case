package com.avmogame.appcent.data.repository


import com.avmogame.appcent.data.entities.Games
import com.avmogame.appcent.data.entities.ResponseGame
import com.avmogame.appcent.data.entities.ResponseGames
import com.avmogame.appcent.data.local.GamesDao
import com.avmogame.appcent.data.remote.RemoteDataSource
import com.avmogame.appcent.util.Resource
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource:GamesDao):IGameRepository {
    override suspend fun getGameDetails(gameId: Int): Resource<ResponseGame> {
        return  remoteDataSource.getGameDetails(gameId)
    }

    override suspend fun getGameList(page: Int?): Resource<ResponseGames> {
        return remoteDataSource.getGameList(page)
    }

    override suspend fun setLocalGameData(games: List<Games>) {
        return localDataSource.addGames(games)
    }

    override suspend fun getLocalFavoritesGames(): List<Games> {
      return localDataSource.getFavoriteGames()
    }

    override suspend fun searchGame(search: String): List<Games> {
        return localDataSource.getGameSearch(search)
    }

    override suspend fun getLocalGameData(gameId: Int): Games {
       return localDataSource.getLocalGameData(gameId)
    }

    override suspend fun setFavoritesType(isFav: Boolean, gameId: Int) {
        return localDataSource.setFavoriteType(isFav,gameId)
    }

    override suspend fun getFavoriteType(gameId: Int): Boolean {
       return localDataSource.getFavoriteType(gameId)
    }


}