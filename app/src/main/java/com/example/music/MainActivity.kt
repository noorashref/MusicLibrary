package com.example.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.music.adapter.PageAdapter
import com.example.music.model.remote.*
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // executeRetrofit(TERM, MEDIA, ENTITY, LIMIT)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = PageAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
    }

    fun executeRetrofit( term:String,media:String,entity:String,limit:String){
        Api.initRetrofit().getMusicList(term,media,entity,limit)
            .enqueue(object : Callback<MusicResponse>{
                override fun onResponse(call: Call<MusicResponse>, response: Response<MusicResponse>) {
                    Log.d(TAG, "onResponse: ${response.body()?.results?.get(5)?.artistName}")
                }

                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            })
    }
}