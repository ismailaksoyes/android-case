package com.avmogame.appcent.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avmogame.appcent.data.entities.GameDetailsData
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.data.repository.Repository
import com.avmogame.appcent.util.Resource
import com.avmogame.appcent.util.toGameData
import com.avmogame.appcent.util.toGameDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _gameDetails = MutableStateFlow<DetailState>(DetailState.Empty)
    val gameDetails: StateFlow<DetailState> = _gameDetails

    val favoriteType = MutableLiveData<Boolean>()

    private var gameId: Int = -1

    sealed class DetailState {
        object Empty : DetailState()
        data class DetailsData(val gameData: GameDetailsData) : DetailState()
    }

    suspend fun getGameDetails() {
        viewModelScope.launch {
            when (val response = repository.getGameDetails(gameId)) {
                is Resource.Success -> {
                    response.data?.let { itData ->
                        _gameDetails.value = DetailState.DetailsData(itData.toGameDetails())
                    }

                }

            }
        }
    }

    suspend fun getFavoriteStatus() {
        viewModelScope.launch {
            val response = repository.getFavoriteType(gameId)
            favoriteType.postValue(response)
        }
    }

    fun setFavoritesType() {
        viewModelScope.launch {
            favoriteType.value?.let { itValue ->
                repository.setFavoritesType(!itValue, gameId)
                getFavoriteStatus()
            }

        }
    }

    fun setGameId(gameId: Int) {
        this.gameId = gameId
    }


}