package com.avmogame.appcent.data.remote

import com.avmogame.appcent.data.entities.ResponseGames
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGamesList(
        @Query("page") page:Int?
    ):Response<ResponseGames>

    @GET("games/{gameId}")
    suspend fun getGameDetails(
        @Path("gameId") gameId:Int
    ):Response<ResponseGames>

}