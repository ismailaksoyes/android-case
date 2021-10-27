package com.avmogame.appcent.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "games",indices = [Index(value = arrayOf("gameId") ,unique = true)])
data class Games(
    @PrimaryKey(autoGenerate = true)
    var uid:Long?=null,
    val imageUrl:String?,
    val name:String?,
    val rating:String?,
    val gameId:Int?,
    val released:String?,
    val isFavorites:Boolean? = false
)