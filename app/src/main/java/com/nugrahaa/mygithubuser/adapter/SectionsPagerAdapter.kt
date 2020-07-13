package com.nugrahaa.mygithubuser.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nugrahaa.mygithubuser.R
import com.nugrahaa.mygithubuser.fragment.FollowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var username: String? = null

    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.follower_title,
        R.string.following_title)

    override fun getItem(position: Int): Fragment {
        val fragment = FollowFragment.newInstance("Ari$position")
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}