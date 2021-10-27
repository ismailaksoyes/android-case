package com.avmogame.appcent.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.avmogame.appcent.data.local.GameData
import com.avmogame.appcent.databinding.GameViewpagerItemBinding
import com.avmogame.appcent.util.Signs
import com.avmogame.appcent.util.urlToImage
import com.bumptech.glide.Glide

class SlidePagerAdapter(private val gameItemClick: (GameData)-> Unit) :RecyclerView.Adapter<SlidePagerAdapter.ViewHolder>(){
    private var _gameData = listOf<GameData>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
      val  binding = GameViewpagerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,gameItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(_gameData[position])
    }

    override fun getItemCount(): Int {
      return _gameData.size
    }

    class ViewHolder(private val binding: GameViewpagerItemBinding,private val gameItemClick: (GameData)-> Unit):RecyclerView.ViewHolder(binding.root){
        fun bind(gameData: GameData){
            binding.ivGamePoster.urlToImage(gameData.imageUrl)
            binding.tvGameName.text = gameData.name
            val ratingAndDate = if (gameData.rating!=null && gameData.released != null){
                "${gameData.rating} ${Signs.slash} ${gameData.released}"
            }else{
                "${gameData.rating?:""} ${gameData.released?:""}"
            }
            binding.tvRatingReleased.text = ratingAndDate
            binding.root.setOnClickListener {
                gameItemClick.invoke(gameData)
            }
        }
    }

    fun replaceItems(gameData: List<GameData>){
        this._gameData = gameData
        notifyItemRangeInserted(0,gameData.size -1)
    }


}