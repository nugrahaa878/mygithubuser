package com.nugrahaa.mygithubuser.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.nugrahaa.mygithubuser.R
import com.nugrahaa.mygithubuser.adapter.ListUserAdapter
import com.nugrahaa.mygithubuser.model.GithubUser
import com.nugrahaa.mygithubuser.network.ApiConfig
import kotlinx.android.synthetic.main.fragment_follow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class FollowFragment : Fragment() {

    private lateinit var rvFollowUser: RecyclerView
    private var githubUsers = arrayListOf<GithubUser>()

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

        addFollowApi(username, tab)
    }

    private fun showRecyclerList() {
        rvFollowUser.layoutManager = LinearLayoutManager(activity)
        val listUserAdapter = ListUserAdapter(githubUsers)
        rvFollowUser.adapter = listUserAdapter
    }

    private fun addFollowApi(username: String, tab: String) {
        val client = ApiConfig.getApiService().getFollow("token 3665e72584dd61f52b327a2eede7e07ee188fefa", username, tab)
        progress_follow.visibility = View.VISIBLE
        client.enqueue(object : Callback<ArrayList<GithubUser>> {
            override fun onFailure(call: Call<ArrayList<GithubUser>>, t: Throwable) {
                Toast.makeText(activity, "Gagal di FollowFragment " + t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ArrayList<GithubUser>>,
                response: Response<ArrayList<GithubUser>>
            ) {
                try {
                    val dataArray = response.body() as ArrayList
                    for (data in dataArray) {
                        githubUsers.add(data)
                    }
                    error.visibility = View.INVISIBLE
                    showRecyclerList()
                    Log.d(TAG, "Follower pertama : " + dataArray.get(0).toString())
                    progress_follow.visibility = View.INVISIBLE

                } catch (e: Exception) {
                    error.visibility = View.VISIBLE
                    progress_follow.visibility = View.INVISIBLE
                    Log.d(TAG, "Masuk ke exception " + e.message)
                    e.printStackTrace()
                }
            }
        })
    }
}
