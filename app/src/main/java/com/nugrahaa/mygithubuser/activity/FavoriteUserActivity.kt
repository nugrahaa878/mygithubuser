package com.nugrahaa.mygithubuser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nugrahaa.mygithubuser.R
import com.nugrahaa.mygithubuser.adapter.FavoriteUserAdapter
import com.nugrahaa.mygithubuser.adapter.ListUserAdapter
import com.nugrahaa.mygithubuser.db.UserHelper
import com.nugrahaa.mygithubuser.helper.MappingHelper
import com.nugrahaa.mygithubuser.model.GithubUser
import kotlinx.android.synthetic.main.activity_favorite_user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var userHelper: UserHelper
    private lateinit var adapter: FavoriteUserAdapter
    private var githubUsers = arrayListOf<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_user)

        supportActionBar?.title = "Favorite"

        rv_favorite.layoutManager = LinearLayoutManager(this)
        rv_favorite.setHasFixedSize(true)
        adapter = FavoriteUserAdapter(this)
        rv_favorite.adapter = adapter

        userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()

        loadNotesAsync()
    }

    private fun loadNotesAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferredUser = async(Dispatchers.IO) {
                val cursor = userHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val users = deferredUser.await()
            users.add(GithubUser("Ari", "Ari", null, "Depok, Indonesia", "Universitas Indonesia", "23", "23", "23", null, 2))
            users.add(GithubUser("Udin", "Udin", null, "Bandung, Indonesia", "Universitas Indonesia", "23", "23", "23", null, 2))
            if (users.size > 0) {
                adapter.listUser = users
            } else {
                adapter.listUser = ArrayList()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        userHelper.close()
    }
}