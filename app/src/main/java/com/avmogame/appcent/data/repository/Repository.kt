package com.avmogame.appcent.data.repository


import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.avmogame.appcent.data.remote.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {


     suspend fun getGameDetails(gameId:Int) = remoteDataSource.getGameDetails(gameId)

     fun getGamePagingFlow() = Pager(PagingConfig(pageSize = 1)){
               remoteDataSource.getGamesSource()
          }.flow



}