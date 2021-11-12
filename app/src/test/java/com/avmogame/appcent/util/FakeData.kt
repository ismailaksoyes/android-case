package com.avmogame.appcent.util

import com.avmogame.appcent.data.entities.Platform
import com.avmogame.appcent.data.entities.Platforms
import com.avmogame.appcent.data.entities.ResponseGame
import com.avmogame.appcent.data.entities.ResponseGames

object FakeData {

    val responseGame = ResponseGame(
        id = 1,
        name = "name",
        released = "released",
        backgroundImage = "image.url",
        metacritic = 70,
        platforms = null,
        genres = null,
        clip = null,
        publishers = listOf(),
        descriptionRaw = "descriptionRaw",
        rating ="rating"
    )

    val responseGames =ResponseGames(
            count = 0,
        next = "next.url",
        previous = "previous.url",
            results = listOf(ResponseGame(
                id = 1,
                name = "name",
                released = "released",
                backgroundImage = "image.url",
                metacritic = 70,
                platforms = null,
                genres = null,
                clip = null,
                publishers = listOf(),
                descriptionRaw = "descriptionRaw",
                rating ="rating"
            ),ResponseGame(
                id = 2,
                name = "name2",
                released = "released2",
                backgroundImage = "image.url2",
                metacritic = 70,
                platforms = null,
                genres = null,
                clip = null,
                publishers = listOf(),
                descriptionRaw = "descriptionRaw2",
                rating ="rating2"
            ),ResponseGame(
                id = 3,
                name = "name3",
                released = "released3",
                backgroundImage = "image.url3",
                metacritic = 70,
                platforms = null,
                genres = null,
                clip = null,
                publishers = listOf(),
                descriptionRaw = "descriptionRaw3",
                rating ="rating3"
            ))
            )


}