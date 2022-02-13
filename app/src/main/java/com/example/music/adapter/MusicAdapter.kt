package com.example.music.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music.databinding.ItemLayoutBinding
import com.example.music.model.remote.MusicItem

private const val TAG = "MusicAdapter"

public class MusicAdapter(private val results : List<MusicItem>,
                          private val listener : OnItemClickListener ) :
    RecyclerView.Adapter<MusicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MusicViewHolder (
        ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false),listener
    )


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        Log.d(TAG, "onBind: ${results.get(position).artistName} ")
        holder.onBind(results[position])
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItem:${results.size}")
      return results.size
    }

    //new
    interface OnItemClickListener{
        fun onItemClick(position :Int)
    }

}