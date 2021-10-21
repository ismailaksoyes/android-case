package com.avmogame.appcent.data.api

import com.avmogame.appcent.data.remote.ResponseGame
import com.avmogame.appcent.data.remote.ResponseGames
import com.avmogame.appcent.util.Resource

interface ApiHelper {
    suspend fun getGamesList(page:Int?):Resource<ResponseGames>
    suspend fun getGameDetails(gameId:String):Resource<ResponseGame>
    suspend fun getSearchGame(search:String):Resource<ResponseGames>

}