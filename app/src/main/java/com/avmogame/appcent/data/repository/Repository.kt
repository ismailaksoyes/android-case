package com.avmogame.appcent.data.repository


import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.avmogame.appcent.data.entities.Games
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.data.local.GamesDao
import com.avmogame.appcent.data.remote.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource,private val localDataSource:GamesDao) {


     suspend fun getGameDetails(gameId:Int) = remoteDataSource.getGameDetails(gameId)

    suspend fun getGameList(page:Int?) = remoteDataSource.getGameList(page)

    suspend fun setLocalGameData(games:List<Games>){
        localDataSource.addGames(games)
    }
    suspend fun getLocalFavoritesGames() = localDataSource.getFavoriteGames()

    suspend fun searchGame(search: String) =  localDataSource.getGameSearch(search)

    suspend fun getLocalGameData(gameId: Int) = localDataSource.getLocalGameData(gameId)

    suspend fun setFavoritesType(isFav:Boolean,gameId: Int) = localDataSource.setFavoriteType(isFav,gameId)

    suspend fun getFavoriteType(gameId: Int) = localDataSource.getFavoriteType(gameId)

}