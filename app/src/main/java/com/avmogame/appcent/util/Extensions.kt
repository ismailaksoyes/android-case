package com.avmogame.appcent.util

import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.lifecycle.Transformations.map
import com.avmogame.appcent.data.entities.GameDetailsData
import com.avmogame.appcent.data.entities.Games
import com.avmogame.appcent.data.entities.ResponseGame
import com.avmogame.appcent.data.entities.ResponseGames
import com.avmogame.appcent.data.local.GameData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun ImageView.urlToImage(url:String?){
    url?.let { itUrl->

        Glide.with(context)
            .load(itUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade(150))
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

fun Games.toGameData() = GameData(
    imageUrl = imageUrl,
    name = name,
    rating = rating,
    gameId = gameId,
    released = released

)

fun List<ResponseGame>.toGamesData() = map{
    GameData(
        imageUrl = it.backgroundImage,
        name = it.name,
        rating = it.rating,
        gameId = it.id,
        released = it.released,
        isFavorites = false


    )
}

fun List<GameData>.toGameEntity() = map {
    Games(
        imageUrl = it.imageUrl,
        name = it.name,
        rating = it.rating,
        gameId = it.gameId,
        released = it.released
    )
}

fun List<Games>.toGameData() = map {
    GameData(
    imageUrl = it.imageUrl,
        name = it.name,
        rating = it.rating,
        gameId = it.gameId,
        released = it.released,
        isFavorites = it.isFavorites
    )
}

fun ResponseGame.toGameDetails() = GameDetailsData(
    gameDesc = descriptionRaw,
    metacritic = metacritic
)

fun View.fadeIn(durationMillis: Long = 250) {
    this.startAnimation(AlphaAnimation(0F, 1F).apply {
        duration = durationMillis
        fillAfter = true
    })
}