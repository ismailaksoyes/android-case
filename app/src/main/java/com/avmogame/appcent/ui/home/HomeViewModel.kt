package com.avmogame.appcent.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.data.repository.IGameRepository
import com.avmogame.appcent.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: IGameRepository) : ViewModel() {

    private val _gameList = MutableStateFlow<GamesState>(GamesState.Empty)
    val gameList: StateFlow<GamesState> = _gameList

    private val _gameSlideList = MutableStateFlow<SlideState>(SlideState.Empty)
    val gameSlideList: StateFlow<SlideState> = _gameSlideList
    val tempList: ArrayList<GameData> = arrayListOf()
    val searchResult = MutableLiveData<List<GameData>>()
    val gameAdapterState = MutableLiveData(GameAdapterState.FEED_STATE)
    private val firstPage = 1
    var currentPage = firstPage

    init {
        loadGames(firstPage)
    }

    sealed class GamesState {
        object Empty : GamesState()
        data class GamesData(val gameData: List<GameData>) : GamesState()
    }



    sealed class SlideState {
        object Empty : SlideState()
        data class SlideData(val gameData: List<GameData>) : SlideState()
    }



    fun loadGames(page: Int?) {
        viewModelScope.launch {
            page?.let { itPage ->
                when (val response = repository.getGameList(page)) {
                    is Resource.Success -> {
                        response.data?.let { itData ->
                            currentPage = page
                            val result = itData.results.toGamesData()
                            setLocalData(result)
                            if (itPage == firstPage) {
                                val firstData = firstGameData(result)
                                slideData(firstData.first)
                                gamesData(firstData.second)
                                tempList.addAll(firstData.second)
                            } else {
                                gamesData(result)
                                tempList.addAll(result)
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
        _gameSlideList.value = SlideState.SlideData(gameData)

    }

    private fun gamesData(gameData: List<GameData>) {

        _gameList.value = GamesState.GamesData(gameData)

    }

    suspend fun searchQuery(keyword: String) {
        searchResult.postValue(repository.searchGame(keyword).toGameData())
    }

    fun setGameState(currentState: GameAdapterState) {
        gameAdapterState.postValue(currentState)
    }
}