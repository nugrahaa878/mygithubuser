package com.nugrahaa.githubuserconsumerapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var rvGithubUser: RecyclerView
    private lateinit var listUserAdapter: FavoriteUserAdapter
    private var githubUsers = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvGithubUser = rv_user
        rvGithubUser.setHasFixedSize(true)

        addItem()
    }

    private fun showRecyclerList() {
        rvGithubUser.layoutManager = LinearLayoutManager(this)
        listUserAdapter = FavoriteUserAdapter()
        listUserAdapter.setData(githubUsers)
        rvGithubUser.adapter = listUserAdapter
    }

    private fun addItem() {

        val table = "user_table"
        val authority = "com.nugrahaa.mygithubuser"

        val uri: Uri = Uri.Builder()
            .scheme("content")
            .authority(authority)
            .appendPath(table)
            .build()

        val contentResolver = this.contentResolver
        val cursor = contentResolver.query(
            uri,
            null,
            null,
            null,
            null
        )

        Toast.makeText(this, cursor.toString(), Toast.LENGTH_LONG).show()
    }
}
