package com.nugrahaa.mygithubuser.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nugrahaa.mygithubuser.R
import com.nugrahaa.mygithubuser.fragment.FollowFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object {
        private const val TAG = "SectionsPagerAdapter"
    }

    var username: String? = null

    @StringRes
    private val TAB_TITLES = arrayOf(
        R.string.followers,
        R.string.following)

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return FollowFragment.newInstance(username, "followers")
        } else {
            return FollowFragment.newInstance(username, "following")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}