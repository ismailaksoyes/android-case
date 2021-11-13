package com.avmogame.appcent.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import app.cash.turbine.test
import com.avmogame.appcent.data.local.GameData

import com.avmogame.appcent.repository.FakeRepository
import com.avmogame.appcent.util.*
import com.avmogame.appcent.util.FakeData.responseGames
import com.google.common.base.CharMatcher.`is`
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*


import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var repository: FakeRepository

    private lateinit var viewModel: HomeViewModel


    @Before
    fun setup() {
       repository = FakeRepository()
        viewModel = HomeViewModel(repository)
    }


    @Test
    fun `get game data list`() {
        coroutinesTestRule.runBlockingTest {

            viewModel.gameList.test {
                val testList = responseGames.results.toGamesData().drop(3)
                val gameData = (awaitItem() as HomeViewModel.GamesState.GamesData).gameData
               assertThat(gameData).isEqualTo(testList)

            }

        }
    }

    @Test
    fun `get slide data list`() = coroutinesTestRule.runBlockingTest {
        viewModel.gameSlideList.test {
            val testList = responseGames.results.toGamesData().take(3)
            val slideData = (awaitItem() as HomeViewModel.SlideState.SlideData).gameData
            assertThat(slideData).isEqualTo(testList)

        }
    }

    @Test
    fun `search game success`() = coroutinesTestRule.runBlockingTest {
        viewModel.searchQuery("name")
        val searchResult = viewModel.searchResult.getOrAwaitValue()
        assertThat(searchResult.isNotEmpty())
    }

    @Test
    fun `search game no more`() = coroutinesTestRule.runBlockingTest {
        viewModel.searchQuery("nomore")
        val searchResult = viewModel.searchResult.getOrAwaitValue()
        assertThat(searchResult.isEmpty())
    }





}


