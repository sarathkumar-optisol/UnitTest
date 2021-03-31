package com.example.unittest.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by SARATH on 31-03-2021
 */
class ViewPagerAdapter(val items : ArrayList<Fragment>, activity : AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}