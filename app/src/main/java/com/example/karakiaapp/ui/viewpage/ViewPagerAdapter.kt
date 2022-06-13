package com.example.karakiaapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.karakiaapp.data.KarakiaItem


class ViewPagerAdapter(activity: FragmentActivity,val exampleList: ArrayList<KarakiaItem>): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return ViewPagerFragment(position,exampleList)
    }
}