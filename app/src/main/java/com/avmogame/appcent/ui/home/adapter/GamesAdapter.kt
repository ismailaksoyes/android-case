package com.avmogame.appcent.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.databinding.GameListItemBinding
import com.avmogame.appcent.util.urlToImage

class GamesAdapter : PagingDataAdapter<GameData, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GameData>() {
            override fun areItemsTheSame(oldItem: GameData, newItem: GameData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GameData, newItem: GameData): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            GameListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(binding)
    }

    class GamesViewHolder(private val binding: GameListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gameData: GameData?) {
            gameData?.let { itGame ->
                binding.ivGamePoster.urlToImage(itGame.imageUrl)
                binding.tvGameName.text = itGame.name
                binding.tvRatingReleased.text = "${itGame.rating} / ${itGame.released}"

            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? GamesViewHolder)?.bind(getItem(position))
    }

    /**
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
     **/

}