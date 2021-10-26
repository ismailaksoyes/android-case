package com.avmogame.appcent.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Publisher(
    @SerializedName("name")
    val name: String
)
