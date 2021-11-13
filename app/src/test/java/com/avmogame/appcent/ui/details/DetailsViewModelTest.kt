package com.avmogame.appcent.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.avmogame.appcent.repository.FakeRepository
import com.avmogame.appcent.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import com.google.common.truth.Truth.assertThat

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DetailsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var repository: FakeRepository

    private lateinit var viewModel:DetailsViewModel

    @Before
    fun setUp() {
        repository = FakeRepository()
        viewModel = DetailsViewModel(repository)
    }

    @Test
    fun `get game details success`(){
        coroutinesTestRule.runBlockingTest {
            viewModel.setGameId(1)
            viewModel.getGameDetails()
            viewModel.gameDetails.test {
                val detailsGame = awaitItem() as DetailsViewModel.DetailState.DetailsData
                assertThat(detailsGame.gameData).isNotNull()
            }
        }
    }

    @Test
    fun `get game details is empty`(){
        coroutinesTestRule.runBlockingTest {
            viewModel.setGameId(-1)
            viewModel.getGameDetails()
            viewModel.gameDetails.test {
                assertThat(awaitItem() is DetailsViewModel.DetailState.Error)
            }
        }
    }
}