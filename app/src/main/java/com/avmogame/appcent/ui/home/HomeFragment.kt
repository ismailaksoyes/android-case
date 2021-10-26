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
import androidx.recyclerview.widget.RecyclerView
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

    private var isLoading: Boolean = false

    private lateinit var layoutManager: LinearLayoutManager



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
        layoutManager = LinearLayoutManager(view.context)
        setupGamesAdapter()
        setupSlideViewPager()
        setSlideData()
        setGameData()
        observeGamesData()
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


        gameAdapter.addLoadStateListener {itState->
            if (itState.refresh is LoadState.Loading && gameAdapter.itemCount == 0){

            }

        }


    }

    private fun scrollListener(){
        binding.rvGames.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView.layoutManager?.let { itLayoutManager ->
                    if (!isLoading && itLayoutManager.itemCount == (layoutManager.findLastVisibleItemPosition() + 1) && itLayoutManager.itemCount > 1) {
                       // loadData(itLayoutManager.itemCount)
                        isLoading = true
                    }

                }
            }

        })
    }
    fun loadGamesData(page:Int?){
        page?.let { itPage->
            lifecycleScope.launch {
                viewModel.loadGames(itPage)
            }
        }
    }

    fun observeGamesData(){
        lifecycleScope.launch {
            viewModel.gameList.collect {
                when(it){
                    is HomeViewModel.GamesState.SlideData->{
                        slideAdapter.replaceItems(it.gameData)
                    }
                    is HomeViewModel.GamesState.GamesData->{

                    }
                }
            }
        }
    }
    private fun setupSlideViewPager(){
        binding.pager2.adapter = slideAdapter
    }

    private fun setupGamesAdapter(){
        binding.rvGames.adapter = gameAdapter
        binding.rvGames.layoutManager = layoutManager
    }



}
