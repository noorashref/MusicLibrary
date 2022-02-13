package com.example.music.adapter

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.databinding.ItemLayoutBinding
import com.example.music.model.remote.MusicItem
import com.example.music.view.RockFragment
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.io.InputStream

private const val TAG = "Music Url testing---- "

class MusicViewHolder(private val binding: ItemLayoutBinding,
                      private val listener : MusicAdapter.OnItemClickListener
)
    :RecyclerView.ViewHolder(binding.root),View.OnClickListener {


    var mMediaPlayer: MediaPlayer? = null


    //new
    init {
        itemView.setOnClickListener(this)
    }

    fun onBind(musicItem :MusicItem) {

        binding.tvArtistName.text =  musicItem.artistName
        binding.tvCollectionName.text = musicItem.collectionName
        binding.tvPrice.text = musicItem.trackPrice.toString()
        Picasso.get().load(musicItem.artworkUrl60).into(binding.ivAlbumImage)

        //Playing Music
         var play_Song = binding.lyPlayMusic.setOnClickListener{
             mMediaPlayer = MediaPlayer().apply {
                 setAudioAttributes(
                     AudioAttributes.Builder()
                         .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                         .setUsage(AudioAttributes.USAGE_MEDIA)
                         .build()
                 )
                 setDataSource(musicItem.previewUrl)
                 prepare()
                 start()
         }
             binding.btn.setOnClickListener{
                 if(mMediaPlayer!!.isPlaying){
                     mMediaPlayer!!.stop()
                 }

         }
         }


//       val value = play_Song
//        Log.d(TAG, "Music url testing----: ${play_Song},${value::class.qualifiedName}")
    }


    //new
    override fun onClick(p0: View?) {
        val position : Int = adapterPosition
        if(position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position)
        }
    }
    //Audio Player Function
    private fun playSong() {
    }
}