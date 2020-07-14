package com.nugrahaa.mygithubuser.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.nugrahaa.mygithubuser.R
import kotlinx.android.synthetic.main.fragment_follow.*

/**
 * A simple [Fragment] subclass.
 */
class FollowFragment : Fragment() {

    private lateinit var rvFollowUser: RecyclerView

    companion object {
        private const val TAG = "FollowFragment"
        private val ARG_USERNAME = "username"
        private val ARG_TAB = "follow"

        fun newInstance(username: String?, status: String?): FollowFragment {
            val fragment = FollowFragment()
            val bundle = Bundle()
            bundle.putString(ARG_TAB, status)
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var username = "Ari"
        var tab = "Follow"
        if (arguments != null) {
            username = arguments?.getString(ARG_USERNAME, "Ari") as String
            tab = arguments?.getString(ARG_TAB, "Follow") as String
        }

        Log.d(TAG, "Ini adalah dari fragment dengan nama " + username)
        rvFollowUser = rv_follow
        rvFollowUser.setHasFixedSize(true)

        tv_tab.text = "Ini adalah tab $tab"


    }
}
