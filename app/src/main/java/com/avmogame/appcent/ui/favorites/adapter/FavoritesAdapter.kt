package com.avmogame.appcent.ui.favorites.adapter

import androidx.recyclerview.widget.RecyclerView


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.ObjectsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.databinding.GameListItemBinding
import com.avmogame.appcent.ui.home.adapter.GameViewHolder
import com.avmogame.appcent.util.Signs
import com.avmogame.appcent.util.urlToImage


class FavoritesAdapter(private val gameItemClick: (GameData) -> Unit) :
    ListAdapter<GameData, FavoritesHolder>(
        DiffCallback()
    ) {

    private var list = mutableListOf<GameData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        val binding =
            GameListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesHolder(binding, gameItemClick)
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(list: List<GameData>) {
        this.list = list.toMutableList()
        submitList(list)
    }

}

class FavoritesHolder(
    private val binding: GameListItemBinding,
    private val favoriteItemClick: (GameData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(gameData: GameData) {
        binding.ivGamePoster.urlToImage(gameData.imageUrl)
        binding.tvGameName.text = gameData.name
        val ratingAndDate = if (gameData.rating != null && gameData.released != null) {
            "${gameData.rating} ${Signs.slash} ${gameData.released}"
        } else {
            "${gameData.rating ?: ""} ${gameData.released ?: ""}"
        }
        binding.tvRatingReleased.text = ratingAndDate
        binding.cvGameList.setOnClickListener {
            favoriteItemClick.invoke(gameData)
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<GameData>() {

    override fun areItemsTheSame(oldItem: GameData, newItem: GameData): Boolean {
        return oldItem.gameId == newItem.gameId
    }

    override fun areContentsTheSame(oldItem: GameData, newItem: GameData): Boolean {
        return ObjectsCompat.equals(oldItem, newItem)
    }
}