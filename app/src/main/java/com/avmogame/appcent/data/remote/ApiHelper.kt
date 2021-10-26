package com.avmogame.appcent.data.remote

import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService):BaseDataSource() {

    suspend fun getGamesList(page:Int?) = getResult { apiService.getGamesList(page) }
    suspend fun getGameDetails(gameId:Int) = getResult { apiService.getGameDetails(gameId) }
}