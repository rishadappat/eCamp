package com.appat.ecamp.ApplicationModules.Dashboard.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.appat.ecamp.ApplicationModules.Dashboard.Fragments.HomeFragment
import com.appat.ecamp.ApplicationModules.Dashboard.Fragments.MediaFragment
import com.appat.ecamp.ApplicationModules.Dashboard.Fragments.MoreFragment

class MainBottomBarViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val homeFragment = HomeFragment()
    private val mediaFragment = MediaFragment()
    private val moreFragment = MoreFragment()

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> homeFragment
            1 -> mediaFragment
            else -> moreFragment
        }
    }
}