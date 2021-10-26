package com.avmogame.appcent.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avmogame.appcent.data.entities.ResponseGames
import com.avmogame.appcent.data.repository.Repository
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.util.Resource
import com.avmogame.appcent.util.toGameEntity
import com.avmogame.appcent.util.toGamesData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _gameList = MutableStateFlow<GamesState>(GamesState.Empty)
    val gameList: StateFlow<GamesState> = _gameList

    private val firstPage = 1

    init {
        loadGames(firstPage)
    }

    sealed class GamesState {
        object Empty : GamesState()
        object Loading : GamesState()
        data class GamesData(val gameData: List<GameData>) : GamesState()
        data class SlideData(val gameData: List<GameData>) : GamesState()
    }


    fun loadGames(page: Int?) {
        viewModelScope.launch {
            page?.let { itPage ->
                when (val response = repository.getGameList(page)) {
                    is Resource.Success -> {
                        response.data?.let { itData ->
                            val result = itData.results.toGamesData()
                            setLocalData(result)
                            if (itPage == firstPage) {
                                val firstData = firstGameData(result)
                                slideData(firstData.first)
                                gamesData(firstData.second)
                            } else {
                                gamesData(result)
                            }
                        }

                    }
                }


            }

        }

    }

   private fun firstGameData(games: List<GameData>): Pair<List<GameData>, List<GameData>> {
        val slideData = games.take(3)
        val gamesData = games.drop(3)
        return Pair(slideData, gamesData)
    }


    private fun setLocalData(games: List<GameData>) {
        viewModelScope.launch {
            repository.setLocalGameData(games.toGameEntity())
        }
    }


    private fun slideData(gameData: List<GameData>) {

        _gameList.value = GamesState.SlideData(gameData)

    }

    private fun gamesData(gameData: List<GameData>) {

        _gameList.value = GamesState.GamesData(gameData)

    }
}