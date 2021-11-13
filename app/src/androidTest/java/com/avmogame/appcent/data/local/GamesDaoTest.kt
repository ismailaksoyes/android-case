package com.avmogame.appcent.data.local


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.avmogame.appcent.data.entities.Games
import com.avmogame.appcent.utils.CoroutineTestRule
import com.avmogame.appcent.utils.GameObject

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import com.google.common.truth.Truth.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule



@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class GamesDaoTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: AppDatabase
    private lateinit var dao:GamesDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.gamesDao
    }

    @After
    fun down(){
        database.close()
    }

    @Test
    fun insertGames() = coroutinesTestRule.runBlockingTest {
        val games = GameObject.singleGame
        dao.addGames(games)
        val allGames = dao.getAllGames()
        assertThat(allGames).containsAnyIn(games)
    }

    @Test
    fun setFavorites() = coroutinesTestRule.runBlockingTest {
        val games = GameObject.singleGame
        dao.addGames(games)
        dao.setFavoriteType(true,1)
        val favoriteType = dao.getLocalGameData(1).isFavorites
        assertThat(favoriteType==true)

    }

    @Test
    fun getGameSearch() = coroutinesTestRule.runBlockingTest {
        val games = GameObject.games
        dao.addGames(games)
       val game =  dao.getGameSearch("games1").filter { it.name =="games1" }
        assertThat(game.isNotEmpty())

    }


}