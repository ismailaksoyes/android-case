package com.avmogame.appcent.data.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Publisher(
    @SerializedName("name")
    val name: String
)