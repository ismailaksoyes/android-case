package com.avmogame.appcent.data.remote


import com.avmogame.appcent.util.Resource
import retrofit2.Response



abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.Success(body)
            }
            return error("${response.code()}")


        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }

    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.Error(message)
    }
}

