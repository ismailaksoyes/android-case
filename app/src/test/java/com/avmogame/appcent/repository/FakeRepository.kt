package com.avmogame.appcent.repository

import com.avmogame.appcent.data.entities.Games
import com.avmogame.appcent.data.entities.ResponseGame
import com.avmogame.appcent.data.entities.ResponseGames
import com.avmogame.appcent.data.repository.IGameRepository
import com.avmogame.appcent.util.FakeData.responseGame
import com.avmogame.appcent.util.FakeData.responseGames
import com.avmogame.appcent.util.Resource
import javax.inject.Inject


class FakeRepository @Inject constructor() :IGameRepository {


    override suspend fun getGameDetails(gameId: Int): Resource<ResponseGame> {
      return  if (responseGame.id == gameId){
            Resource.Success(responseGame)
        }else{
            Resource.Error("")
        }
    }

    override suspend fun getGameList(page: Int?): Resource<ResponseGames> {
        return Resource.Success(responseGames)
    }

    override suspend fun setLocalGameData(games: List<Games>) {

    }

    override suspend fun getLocalFavoritesGames(): List<Games> {
        return listOf(
            Games(
                uid = 1,
                imageUrl = "image.url",
                name = "name",
                rating = "rating",
                gameId = 1,
                released = "released",
                isFavorites = false
            )
        )
    }

    override suspend fun searchGame(search: String): List<Games> {
        return listOf(
            Games(
                uid = 1,
                imageUrl = "image.url",
                name = "name",
                rating = "rating",
                gameId = 1,
                released = "released",
                isFavorites = false
            )
        ).filter { it.name ==search }
    }

    override suspend fun getLocalGameData(gameId: Int): Games {
        return Games(
                uid = 1,
                imageUrl = "image.url",
                name = "name",
                rating = "rating",
                gameId = 1,
                released = "released",
                isFavorites = false
            )
    }

    override suspend fun setFavoritesType(isFav: Boolean, gameId: Int) {

    }

    override suspend fun getFavoriteType(gameId: Int): Boolean {
      return true
    }
}