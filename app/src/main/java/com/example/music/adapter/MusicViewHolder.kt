package com.example.music.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.databinding.ItemLayoutBinding
import com.example.music.model.remote.MusicItem
import com.squareup.picasso.Picasso

class MusicViewHolder(private val binding: ItemLayoutBinding,
                      private val listener : MusicAdapter.OnItemClickListener
)
    :RecyclerView.ViewHolder(binding.root),View.OnClickListener {

    //new
    init {
        itemView.setOnClickListener(this)
    }

    fun onBind(musicItem :MusicItem) {

        binding.tvArtistName.text =  musicItem.artistName
        binding.tvCollectionName.text = musicItem.collectionName
        binding.tvPrice.text = musicItem.trackPrice.toString()
        Picasso.get().load(musicItem.artworkUrl60).into(binding.ivAlbumImage)
    }

    //new
    override fun onClick(p0: View?) {
        val position : Int = adapterPosition
        if(position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position)
        }
    }


}