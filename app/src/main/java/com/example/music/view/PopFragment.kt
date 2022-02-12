package com.example.music.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music.R
import com.example.music.adapter.MusicAdapter
import com.example.music.databinding.FragmentPopBinding
import com.example.music.model.remote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PopFragment"

class PopFragment : Fragment(),MusicAdapter.OnItemClickListener {
   
    private lateinit var binding: FragmentPopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentPopBinding.inflate(inflater,container,false)
        executeRetrofit(TERM, MEDIA, ENTITY, LIMIT)
        return binding.root
    }

    fun executeRetrofit( term:String,media:String,entity:String,limit:String){
        var term = "pop"
        Api.initRetrofit().getMusicList(term,media,entity,limit)
            .enqueue(object : Callback<MusicResponse> {
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

    private fun initViews(results: List<MusicItem>) {

        Log.d(TAG, "iniviews: ${results?.size}")
        binding.recyclerViewPop.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewPop.adapter = MusicAdapter(results,this)
        binding.recyclerViewPop?.adapter?.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

}