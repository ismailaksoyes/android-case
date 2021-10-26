package com.avmogame.appcent.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.databinding.GameViewpagerItemBinding
import com.avmogame.appcent.util.urlToImage
import com.bumptech.glide.Glide

class SlidePagerAdapter() :RecyclerView.Adapter<SlidePagerAdapter.ViewHolder>(){
    private var _gameData = listOf<GameData>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
      val  binding = GameViewpagerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_gameData[position])
    }

    override fun getItemCount(): Int {
      return _gameData.size
    }

    class ViewHolder(private val binding: GameViewpagerItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(gameData: GameData){
            binding.ivGamePoster.urlToImage(gameData.imageUrl)
            binding.tvGameName.text = gameData.name
            binding.tvRatingReleased.text = "${gameData.rating} / ${gameData.released}"
        }
    }

    fun replaceItems(gameData: List<GameData>){
        this._gameData = gameData
        notifyDataSetChanged()
    }


}