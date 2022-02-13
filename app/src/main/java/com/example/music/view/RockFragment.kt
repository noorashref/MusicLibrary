package com.example.music.view

import android.app.Activity
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music.R
import com.example.music.adapter.MusicAdapter
import com.example.music.databinding.FragmentRockBinding
import com.example.music.model.remote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**"artistName":
"collectionName":
"artworkUrl60":
"trackPrice":
*/

private const val TAG = "RockFragment"

class RockFragment : Fragment(),MusicAdapter.OnItemClickListener {

    private var mediaPlayer : MediaPlayer? =null

    private lateinit var binding: FragmentRockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRockBinding.inflate(inflater, container, false)
        executeRetrofit(TERM, MEDIA, ENTITY, LIMIT)
        return binding.root
    }

    //Old
    private fun initViews(results: List<MusicItem>) {
        Log.d(TAG, "iniviews: ${results?.size}")
        binding.recyclerViewRock.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewRock.adapter = MusicAdapter(results,this)
        binding.recyclerViewRock?.adapter?.notifyDataSetChanged()
    }


    fun executeRetrofit( term:String,media:String,entity:String,limit:String){
        Api.initRetrofit().getMusicList(term,media,entity,limit)
            .enqueue(object : Callback<MusicResponse>{
                override fun onResponse(call: Call<MusicResponse>, response: Response<MusicResponse>) {
                   response.body()?.let {
                       initViews(it.results)
                       Log.d(TAG, "onResponse--------: ${response.body()?.results?.get(5)?.artistName}")
                   }
                    Log.d(TAG, "onResponse: ${response.body()?.results?.get(5)?.artistName}")
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
    }

    override fun onItemClick(position: Int) {
        Log.d(TAG, "onItemClick: $position")
        Toast.makeText(requireContext(), "$position", Toast.LENGTH_SHORT).show()
    }

    fun playMusic(previewUrl : String){
        MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(previewUrl)
            prepare()
            start()
        }
    }

}