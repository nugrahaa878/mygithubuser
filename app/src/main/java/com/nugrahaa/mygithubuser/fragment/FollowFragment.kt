package com.nugrahaa.mygithubuser.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nugrahaa.mygithubuser.R
import kotlinx.android.synthetic.main.fragment_follow.*

/**
 * A simple [Fragment] subclass.
 */
class FollowFragment : Fragment() {

    companion object {
        private val ARG_USERNAME = "username"

        fun newInstance(username: String): FollowFragment {
            val fragment = FollowFragment()
            val bundle = Bundle()
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
        if (arguments != null) {
            username = arguments?.getString(ARG_USERNAME, "Ari") as String
        }

        section_label.text = "${getString(R.string.this_is)} $username"
    }
}
