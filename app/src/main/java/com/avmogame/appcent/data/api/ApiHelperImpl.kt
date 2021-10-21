package com.avmogame.appcent.data.api

import android.util.Log
import com.avmogame.appcent.data.remote.ResponseGame
import com.avmogame.appcent.data.remote.ResponseGames
import com.avmogame.appcent.util.NetworkCode.NETWORK_ERROR
import com.avmogame.appcent.util.NetworkCode.NO_INTERNET_CONNECTION
import com.avmogame.appcent.util.NetworkConnectivity

import com.avmogame.appcent.util.Resource
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService, private val networkConnectivity: NetworkConnectivity):ApiHelper {
    override suspend fun getGamesList(page: Int?): Resource<ResponseGames> {
        return when(val response = checkResponse { (apiService::getGamesList)(page) }){
            is ResponseGames->Resource.Success(response)
            else->Resource.Error(response as Int)
        }
    }

    override suspend fun getGameDetails(gameId: String): Resource<ResponseGame> {
        return when(val response = checkResponse { (apiService::getGameDetails)(gameId) }){
            is ResponseGame->Resource.Success(response)
            else->Resource.Error(response as Int)
        }
    }

    override suspend fun getSearchGame(search: String): Resource<ResponseGames> {
        return when(val response = checkResponse { (apiService::getSearchGame)(search) }){
            is ResponseGames->Resource.Success(response)
            else->Resource.Error(response as Int)
        }
    }

    private suspend fun checkResponse(call:suspend() -> Response<*>):Any?{
        if (!networkConnectivity.isConnected()){
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = call.invoke()
            val responseCode = response.code()
            if (response.isSuccessful){
                response.body()
            }else{
                responseCode
            }
        } catch (e: IOException){
             NETWORK_ERROR
        }
    }

}