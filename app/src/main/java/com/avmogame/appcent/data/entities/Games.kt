package com.avmogame.appcent.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Games(
    val imageUrl:String?,
    val name:String?,
    val rating:String?,
    @PrimaryKey
    val gameId:Int?,
    val released:String?,
    val isFavorites:Boolean?
)