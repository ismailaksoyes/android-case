package com.avmogame.appcent.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.avmogame.appcent.data.entities.ResponseGames
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.util.Resource
import com.avmogame.appcent.util.toGamesData


import java.lang.Exception

class GamePagingSource(private val apiHelper: ApiHelper) : PagingSource<Int, GameData>() {
    override fun getRefreshKey(state: PagingState<Int, GameData>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameData> {
        try {
            val nextPage = params.key ?: 1
            when (val response = apiHelper.getGamesList(nextPage)) {
                is Resource.Success -> {
                    response.data?.let { itData ->
                        return LoadResult.Page(
                            data = itData.results.toGamesData(),
                            prevKey = if (nextPage == 1) null else nextPage - 1,
                            nextKey = nextPage + 1
                        )
                    }

                }else->{
                    return LoadResult.Error(Throwable(""))
                }
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
        return LoadResult.Error(Throwable(""))
    }
}