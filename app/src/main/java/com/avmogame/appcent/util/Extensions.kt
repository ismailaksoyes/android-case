package com.avmogame.appcent.util

import android.widget.ImageView
import androidx.lifecycle.Transformations.map
import com.avmogame.appcent.data.entities.Games
import com.avmogame.appcent.data.entities.ResponseGame
import com.avmogame.appcent.data.entities.ResponseGames
import com.avmogame.appcent.data.local.GameData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.urlToImage(url:String?){
    url?.let { itUrl->

        Glide.with(context)
            .load(itUrl)
            .centerCrop()
            .into(this)

    }
}

fun ResponseGame.toGameData() = GameData(
    imageUrl = backgroundImage,
    name = name,
    rating = rating,
    gameId = id,
    released = released

)

fun List<ResponseGame>.toGamesData() = map{
    GameData(
        imageUrl = it.backgroundImage,
        name = it.name,
        rating = it.rating,
        gameId = it.id,
        released = it.released

    )
}

fun List<GameData>.toGameEntity() = map {
    Games(
        imageUrl = it.imageUrl,
        name = it.name,
        rating = it.rating,
        gameId = it.gameId,
        released = it.released,
        isFavorites = false
    )
}