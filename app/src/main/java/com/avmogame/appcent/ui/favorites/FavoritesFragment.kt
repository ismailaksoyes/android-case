package com.avmogame.appcent.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.avmogame.appcent.databinding.FragmentFavoritesBinding
import com.avmogame.appcent.ui.details.DetailsFragment.Companion.IS_FAVORITE_STATE_CHANGE
import com.avmogame.appcent.ui.favorites.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    lateinit var binding: FragmentFavoritesBinding

    val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGamesAdapter()
        observeFavoritesData()
        isFavoriteStateChange()
    }

    fun observeFavoritesData() {
        lifecycleScope.launch {
            viewModel.favoritesData.collect {
                when (it) {
                    is FavoritesViewModel.FavoritesState.Success -> {
                        binding.tvNotFound.visibility = View.GONE
                        (binding.rvFavorites.adapter as FavoritesAdapter).setData(it.gameList)
                    }else->{
                        binding.tvNotFound.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setupGamesAdapter() {
        binding.rvFavorites.adapter = FavoritesAdapter { itGameData ->
            val action = FavoritesFragmentDirections.actionDestinationFavoritesToDestinationDetails(
                itGameData
            )
            findNavController().navigate(action)
        }
        binding.rvFavorites.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }

    private fun isFavoriteStateChange() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
            IS_FAVORITE_STATE_CHANGE
        )?.observe(
            viewLifecycleOwner
        ) { isChange ->
            if (isChange) {
                viewModel.getFavoritesGame()
            }
        }
    }


}