package com.avmogame.appcent.util

sealed class Resource<T>(
    val data: T? = null,
    val errorCode: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(errorMsg: String) : Resource<T>(null, errorMsg)

}