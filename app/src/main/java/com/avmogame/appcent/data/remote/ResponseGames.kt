package com.avmogame.appcent.data.remote


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseGames(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<ResponseGame>
)