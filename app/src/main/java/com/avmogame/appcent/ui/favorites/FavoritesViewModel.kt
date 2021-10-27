package com.avmogame.appcent.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.data.repository.Repository
import com.avmogame.appcent.util.toGameData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _favoritesData = MutableStateFlow<FavoritesState>(FavoritesState.Empty)
    val favoritesData: StateFlow<FavoritesState> = _favoritesData

    init {
        getFavoritesGame()
    }

    sealed class FavoritesState {
        object Empty : FavoritesState()
        data class Success(val gameList: List<GameData>) : FavoritesState()
    }

    fun getFavoritesGame() {
        viewModelScope.launch {
            val response = repository.getLocalFavoritesGames()
            if (response.isNotEmpty()) {
                _favoritesData.value = FavoritesState.Success(response.toGameData())
            }
        }
    }

}