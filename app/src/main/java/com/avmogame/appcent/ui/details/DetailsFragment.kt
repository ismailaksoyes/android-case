package com.avmogame.appcent.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.avmogame.appcent.R
import com.avmogame.appcent.data.entities.GameDetailsData
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.databinding.FragmentDetailsBinding
import com.avmogame.appcent.util.fadeIn
import com.avmogame.appcent.util.urlToImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    companion object {
        const val IS_FAVORITE_STATE_CHANGE = "is_favorite_state_change"
    }

    private val args: DetailsFragmentArgs by navArgs()

    lateinit var binding: FragmentDetailsBinding

    val viewModel: DetailsViewModel by viewModels()

    var isFavoriteStateChange: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameData = args.gameData
        gameData.gameId?.let { itGameId ->
            viewModel.setGameId(itGameId)
        }
        initData(gameData)
        getGameDetails()
        observeGameData()
        observeFavoriteStatus()
        actionFavoriteClick()
        getFavoriteStatus()
        initListeners()


    }

    private fun getGameDetails() {
        lifecycleScope.launch {
            viewModel.getGameDetails()
        }
    }

    fun getFavoriteStatus() {
        lifecycleScope.launch {
            viewModel.getFavoriteStatus()
        }
    }

    private fun observeGameData() {
        lifecycleScope.launch {
            viewModel.gameDetails.collect {
                when (it) {
                    is DetailsViewModel.DetailState.DetailsData -> {
                        setGameData(it.gameData)
                    }
                }
            }
        }
    }

    private fun setGameData(detailsData: GameDetailsData) {
        binding.tvDesc.text = detailsData.gameDesc
        detailsData.metacritic?.let { itMeta ->
            binding.tvMetacriticRate.text = itMeta.toString()
        }
    }


    private fun observeFavoriteStatus() {
        lifecycleScope.launch {
            viewModel.favoriteType.observe(viewLifecycleOwner, Observer {
                favoriteChanceColor(it)
            })
        }
    }


    private fun actionFavoriteClick() {
        binding.ivFavorite.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.favorite_anim)
            it.startAnimation(animation)
            isFavoriteStateChange = true
            lifecycleScope.launch {
                viewModel.setFavoritesType()
            }
        }
    }

    private fun favoriteChanceColor(isFav: Boolean) {
        if (isFav) {
            binding.ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_thumb_up_red_24
                )
            )
        } else {
            binding.ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_thumb_up_24
                )
            )
        }
    }

    private fun initData(gameData: GameData) {
        binding.ivGamePoster.urlToImage(gameData.imageUrl)
        binding.tvGameName.text = gameData.name
        binding.tvReleased.text = gameData.released
        binding.tvGameName.fadeIn()
        binding.tvReleased.fadeIn()
        binding.tvDesc.fadeIn()
    }

    private fun initListeners() {
        binding.ivBackIco.setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                IS_FAVORITE_STATE_CHANGE,
                isFavoriteStateChange
            )
            findNavController().popBackStack()
        }
    }

}