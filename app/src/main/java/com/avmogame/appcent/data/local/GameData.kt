package com.avmogame.appcent.data.local

import androidx.annotation.Keep

@Keep
data class GameData(
    val imageUrl:String?,
    val name:String?,
    val rating:String?,
    val gameId:Int?,
    val released:String?,
    val isFavorites:Boolean? = false
)
