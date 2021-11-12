package com.avmogame.appcent.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.avmogame.appcent.data.entities.ResponseGames
import com.avmogame.appcent.repository.FakeRepository
import com.avmogame.appcent.util.CoroutineTestRule
import com.avmogame.appcent.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf


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

    private lateinit var viewModel :HomeViewModel


    @Before
    fun setup(){
        repository = FakeRepository()
        viewModel = HomeViewModel(repository)
    }


    @Test
    fun getGameList() = coroutinesTestRule.runBlockingTest {
        val stateFlow = MutableStateFlow<String>("test")
        stateFlow.test {
            assertEquals("test",expectMostRecentItem())
        }


    }

}


