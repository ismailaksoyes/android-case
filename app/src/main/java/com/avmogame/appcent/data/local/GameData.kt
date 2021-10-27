package com.avmogame.appcent.data.local

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class GameData(
    val imageUrl:String?,
    val name:String?,
    val rating:String?,
    val gameId:Int?,
    val released:String?,
    val isFavorites:Boolean? = false
) : Parcelable
