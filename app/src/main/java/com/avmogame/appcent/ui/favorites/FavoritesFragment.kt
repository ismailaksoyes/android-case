package com.avmogame.appcent.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avmogame.appcent.R
import com.avmogame.appcent.databinding.FragmentFavoritesBinding
import com.avmogame.appcent.ui.favorites.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    lateinit var binding:FragmentFavoritesBinding

    private val favoritesAdapter by lazy { FavoritesAdapter() }

    val viewModel : FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGamesAdapter()
        observeFavoritesData()
    }

    fun observeFavoritesData(){
        lifecycleScope.launch {
            viewModel.favoritesData.collect {
                when(it){
                    is FavoritesViewModel.FavoritesState.Success->{
                        favoritesAdapter.replaceItems(it.gameList)
                    }

                }

            }
        }
    }
    private fun setupGamesAdapter(){
        binding.rvFavorites.adapter = favoritesAdapter
        binding.rvFavorites.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false)
    }


}