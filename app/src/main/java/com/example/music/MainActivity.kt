package com.example.music

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.music.adapter.PageAdapter
import com.example.music.model.remote.*
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout :TabLayout

    private var tabIcons = intArrayOf(
        R.drawable.ic_baseline_library_music_24,
        R.drawable.ic_baseline_headset_mic_24,
        R.drawable.ic_baseline_queue_music_24
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Api data checking
       // executeRetrofit(TERM, MEDIA, ENTITY, LIMIT)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = PageAdapter(supportFragmentManager)

        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
        setupTabIcons()
    }

    //Setting the Layout Tab icons
    private fun setupTabIcons() {
        tabLayout.getTabAt(0)?.setIcon(tabIcons[0])
        tabLayout.getTabAt(1)?.setIcon(tabIcons[1])
        tabLayout.getTabAt(2)?.setIcon(tabIcons[2])
    }

}