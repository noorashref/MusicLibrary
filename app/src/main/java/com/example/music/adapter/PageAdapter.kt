package com.example.music.adapter

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.music.R
import com.example.music.view.ClassicFragment
import com.example.music.view.PopFragment
import com.example.music.view.RockFragment
import com.google.android.material.tabs.TabLayout

class PageAdapter(fragmentManager :FragmentManager) :FragmentPagerAdapter(fragmentManager) {


    override fun getCount(): Int {
       return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return RockFragment()
            1 -> return ClassicFragment()
            2 -> return PopFragment()
            else -> return RockFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> { return "Rock" }
            1 -> { return "Classic" }
            2 -> { return "Pop" }
        }
        return super.getPageTitle(position)
    }


}