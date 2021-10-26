package com.avmogame.appcent.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.avmogame.appcent.data.entities.ResponseGame
import com.avmogame.appcent.data.repository.Repository
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.data.remote.ApiHelper
import com.avmogame.appcent.data.remote.GamePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository):ViewModel(){


    val testData3 = MutableStateFlow(3)

    val datatest3 = MutableLiveData<GameData>()



    val getGameFlow = repository.getGamePagingFlow().cachedIn(viewModelScope)


    val test4989 = getGameFlow.map { it-> it.map { it.name == "Grand Theft Auto V" } }.onStart {  }



   // val test2 = testData.collect {  }
}