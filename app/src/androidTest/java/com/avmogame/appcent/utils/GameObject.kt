package com.avmogame.appcent.utils

import com.avmogame.appcent.data.entities.Games

object GameObject {

    val singleGame = listOf(
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

    val games = listOf(
        Games(
            uid = 1,
            imageUrl = "image.url",
            name = "name",
            rating = "rating",
            gameId = 1,
            released = "released",
            isFavorites = false
        ),
        Games(
            uid = 2,
            imageUrl = "image.url",
            name = "name2",
            rating = "rating",
            gameId = 2,
            released = "released",
            isFavorites = false
        ),
        Games(
            uid = 3,
            imageUrl = "image.url",
            name = "name3",
            rating = "rating",
            gameId = 3,
            released = "released",
            isFavorites = false
        )
    )
}