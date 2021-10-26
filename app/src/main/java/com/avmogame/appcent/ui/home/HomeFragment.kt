package com.avmogame.appcent.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.avmogame.appcent.R
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.databinding.FragmentHomeBinding
import com.avmogame.appcent.ui.home.adapter.GamesAdapter
import com.avmogame.appcent.ui.home.adapter.SlidePagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val slideAdapter by lazy { SlidePagerAdapter() }

    private val gameAdapter by lazy { GamesAdapter() }

    lateinit var binding : FragmentHomeBinding

    val viewModel:HomeViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGamesAdapter()
        setupSlideViewPager()
        setSlideData()
        setGameData()
    }

    private fun setSlideData() {
        val fakeSlideData = ArrayList<GameData>()
        for (i in 1..3) {
            fakeSlideData.add(
                GameData(
                    imageUrl = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
                    name = "Grand Theft Auto V",
                    rating = "4.48",
                    gameId = i,
                    released = "2013-09-17"
                )
            )
        }
        slideAdapter.replaceItems(fakeSlideData)
    }

    private fun setGameData(){
        val fakeSlideData = ArrayList<GameData>()
        for (i in 1..20) {
            fakeSlideData.add(
                GameData(
                    imageUrl = "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
                    name = "Grand Theft Auto V",
                    rating = "4.48",
                    gameId = i,
                    released = "2013-09-17"
                )
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getGameFlow.collect {
                gameAdapter.submitData(it)
            }


        }
        gameAdapter.addLoadStateListener {itState->
            if (itState.refresh is LoadState.Loading && gameAdapter.itemCount == 0){

            }

        }


    }
    private fun setupSlideViewPager(){
        binding.pager2.adapter = slideAdapter
    }

    private fun setupGamesAdapter(){
        binding.rvGames.adapter = gameAdapter
        binding.rvGames.isNestedScrollingEnabled = true
        binding.rvGames.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false)
    }



}
