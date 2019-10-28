package com.example.sixttask.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(
    fm: FragmentManager,
    val fragmentsPairs: List<Pair<Fragment, String>>
) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragmentsPairs[position].first
    }

    override fun getCount(): Int {
        return fragmentsPairs.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentsPairs[position].second
    }
}