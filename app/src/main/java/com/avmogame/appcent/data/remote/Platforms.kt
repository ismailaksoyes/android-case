package com.avmogame.appcent.data.remote

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Platforms(
    @SerializedName("platform")
    val platform: Platform
)
