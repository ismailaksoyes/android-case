package com.avmogame.appcent.data.entities

import androidx.annotation.Keep

@Keep
data class GameDetailsData(
    val gameDesc:String? = null,
    val metacritic:Int? = null,
)
