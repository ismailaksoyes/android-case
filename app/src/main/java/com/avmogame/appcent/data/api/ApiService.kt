package com.avmogame.appcent.data.api

import com.avmogame.appcent.data.remote.ResponseGames
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGamesList(
        @Path("page") page:Int?
    ):Response<ResponseGames>

    @GET("games/{gameId}")
    suspend fun getGameDetails(
        @Path("gameId") gameId:String
    ):Response<ResponseGames>

    @GET("games")
    suspend fun getSearchGame(
        @Query("search") search:String
    ):Response<ResponseGames>
}