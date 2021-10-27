package com.avmogame.appcent.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.databinding.FragmentHomeBinding
import com.avmogame.appcent.ui.home.adapter.GameAdapter
import com.avmogame.appcent.ui.home.adapter.SlidePagerAdapter
import com.avmogame.appcent.util.GameAdapterState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    val viewModel: HomeViewModel by viewModels()

    private var isLoading: Boolean = false

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(view.context)
        setupGamesAdapter()
        setupSlideViewPager()
        observeGamesData()
        observeSlideData()
        scrollListener()
        searchListener()
        observeSearch()
        observeAdapterState()
    }

    private fun observeSearch() {
        viewModel.searchResult.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                setNotFoundViewVisibility(isVisible = true)
            } else {
                setNotFoundViewVisibility(isVisible = false)
            }
            (binding.rvGames.adapter as GameAdapter).submitList(it)
        })
    }

    private fun setNotFoundViewVisibility(isVisible: Boolean) {
        binding.tvNotFound.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

    private fun searchListener() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                if (editable.toString().length >= 3) {
                    sendSearchQuery(editable.toString())
                    setAdapterState(GameAdapterState.SEARCH_STATE)
                } else if (editable.toString().isEmpty()) {
                    showFeed()
                }
            }
        })
    }

    private fun setAdapterState(currentState: GameAdapterState) {
        viewModel.setGameState(currentState)
    }

    private fun sendSearchQuery(keyword: String) {
        lifecycleScope.launch {
            viewModel.searchQuery(keyword)
        }
    }

    private fun showFeed() {
        setNotFoundViewVisibility(isVisible = false)
        setAdapterState(GameAdapterState.FEED_STATE)
        (binding.rvGames.adapter as GameAdapter).setData(viewModel.tempList)
    }

    private fun scrollListener() {
        binding.rvGames.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView.layoutManager?.let { itLayoutManager ->
                    if (!isLoading && itLayoutManager.itemCount == (layoutManager.findLastVisibleItemPosition() + 1) && itLayoutManager.itemCount > 1) {
                        if (viewModel.gameAdapterState.value?.equals(GameAdapterState.FEED_STATE) == true) {
                            isLoading = true
                            loadGamesData(page = viewModel.currentPage.plus(1))
                        }
                    }

                }
            }

        })

    }

    private fun loadGamesData(page: Int?) {
        page?.let { itPage ->
            lifecycleScope.launch {
                viewModel.loadGames(itPage)
            }
        }
    }

    private fun observeGamesData() {
        lifecycleScope.launch {
            viewModel.gameList.collect {
                when (it) {
                    is HomeViewModel.GamesState.GamesData -> {
                        if (binding.rvGames.adapter is GameAdapter) {
                            val oldList: ArrayList<GameData> =
                                ArrayList((binding.rvGames.adapter as GameAdapter).currentList)
                            oldList.addAll(it.gameData)
                            (binding.rvGames.adapter as GameAdapter).setData(oldList)
                            isLoading = false
                        }
                    }
                }
            }
        }
    }

    private fun observeSlideData() {
        lifecycleScope.launch {
            viewModel.gameSlideList.collect {
                when (it) {
                    is HomeViewModel.SlideState.SlideData -> {
                        (binding.pager2.adapter as SlidePagerAdapter).replaceItems(it.gameData)
                    }

                }
            }
        }
    }

    private fun setupSlideViewPager() {
        binding.pager2.adapter = SlidePagerAdapter { itGameData ->
            navigateDetails(itGameData)
        }
    }

    private fun setupGamesAdapter() {
        binding.rvGames.adapter = GameAdapter { itGameData ->
            navigateDetails(itGameData)
        }
        binding.rvGames.itemAnimator = null
        binding.rvGames.layoutManager = layoutManager
    }

    private fun renderSearchState() {
        binding.pager2.visibility = View.GONE
    }

    private fun navigateDetails(gameData: GameData) {

        val action = HomeFragmentDirections.actionDestinationHomeToDestinationDetails(gameData)
        findNavController().navigate(action)

    }

    private fun renderFeedState() {
        binding.pager2.visibility = View.VISIBLE
    }

    private fun observeAdapterState() {
        lifecycleScope.launch {
            viewModel.gameAdapterState.observe(viewLifecycleOwner, Observer {
                when (it) {
                    GameAdapterState.FEED_STATE -> {
                        renderFeedState()
                    }
                    GameAdapterState.SEARCH_STATE -> {
                        renderSearchState()
                    }
                }

            })
        }
    }


}