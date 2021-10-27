package com.avmogame.appcent.ui.favorites.adapter

import androidx.recyclerview.widget.RecyclerView


import android.view.LayoutInflater
import android.view.ViewGroup

import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.databinding.GameListItemBinding
import com.avmogame.appcent.util.urlToImage

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {


    private var _gamesList = listOf<GameData>()

    class ViewHolder(private val binding: GameListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(gameData: GameData){
            binding.ivGamePoster.urlToImage(gameData.imageUrl)
            binding.tvGameName.text = gameData.name
            binding.tvRatingReleased.text = "${gameData.rating} / ${gameData.released}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_gamesList[position])
    }

    override fun getItemCount(): Int {
        return _gamesList.size
    }

    fun replaceItems(gameData: List<GameData>){
        this._gamesList = gameData
        notifyDataSetChanged()
    }


}