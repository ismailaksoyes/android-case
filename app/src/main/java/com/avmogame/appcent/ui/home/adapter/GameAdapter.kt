package com.avmogame.appcent.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.ObjectsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.databinding.GameListItemBinding
import com.avmogame.appcent.util.GameAdapterState
import com.avmogame.appcent.util.SIGNS.slash
import com.avmogame.appcent.util.urlToImage



class GameAdapter(val gameItemIn: GameItemIn) : ListAdapter<GameData, GameViewHolder>(DiffCallback()) {

    private var list = mutableListOf<GameData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding =
            GameListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding,gameItemIn)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(list: List<GameData>){
        this.list = list.toMutableList()
       submitList(list)
    }

}

private class DiffCallback: DiffUtil.ItemCallback<GameData>() {

    override fun areItemsTheSame(oldItem: GameData, newItem: GameData): Boolean {
        return oldItem.gameId == newItem.gameId
    }

    override fun areContentsTheSame(oldItem: GameData, newItem: GameData): Boolean {
        return ObjectsCompat.equals(oldItem, newItem)
    }
}


class GameViewHolder(private val binding: GameListItemBinding,private val gameItemIn: GameItemIn) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(gameData: GameData) {
        binding.ivGamePoster.urlToImage(gameData.imageUrl)
        binding.tvGameName.text = gameData.name
       val ratingAndDate = if (gameData.rating!=null && gameData.released != null){
            "${gameData.rating} $slash ${gameData.released}"
        }else{
            "${gameData.rating?:""} ${gameData.released?:""}"
        }
        binding.tvRatingReleased.text = ratingAndDate
        binding.cvGameList.setOnClickListener { gameItemIn.gameData(gameData) }
    }
}