package com.avmogame.appcent.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.avmogame.appcent.data.entities.ResponseGames
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.util.NetworkConnectivity
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiHelper: ApiHelper,private val gamePagingSource: GamePagingSource) {

    fun getGamesSource() = gamePagingSource
    suspend fun getGameDetails(gameId:Int) = apiHelper.getGameDetails(gameId)


}