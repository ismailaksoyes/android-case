package com.avmogame.appcent.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Platforms(
    @SerializedName("platform")
    val platform: Platform
)
