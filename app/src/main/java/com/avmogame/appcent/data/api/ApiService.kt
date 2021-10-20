package com.avmogame.appcent.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("games")
    suspend fun getGameList(
        @Path("page") page:Int
    ):Response<String>

}