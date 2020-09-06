package com.nugrahaa.mygithubuser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nugrahaa.mygithubuser.R
import com.nugrahaa.mygithubuser.adapter.FavoriteUserAdapter
import com.nugrahaa.mygithubuser.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_favorite_user2.*

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var rvFavoriteUser: RecyclerView
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_user2)

        val adapter = FavoriteUserAdapter()
        rvFavoriteUser = rv_favorite_user
        rvFavoriteUser.adapter = adapter
        rvFavoriteUser.layoutManager = LinearLayoutManager(this)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer { user ->
            adapter.setData(user)
        })

    }
}