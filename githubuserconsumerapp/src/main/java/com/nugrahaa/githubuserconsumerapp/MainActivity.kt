package com.nugrahaa.githubuserconsumerapp

import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*

class MainActivity : AppCompatActivity() {

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

    private fun showRecyclerList(listUser: ArrayList<User>) {
        rvGithubUser.layoutManager = LinearLayoutManager(this)
        listUserAdapter = FavoriteUserAdapter()
        listUserAdapter.setData(listUser)
        rvGithubUser.adapter = listUserAdapter
    }

    @SuppressLint("Recycle")
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

        showRecyclerList(convertCursor(cursor))

    }

    private fun convertCursor(cursor: Cursor?): ArrayList<User> {
        val favUsers = ArrayList<User>()

        cursor?.apply {
            while (moveToNext()) {
                val name = getString(getColumnIndexOrThrow("name"))
                val id = getInt(getColumnIndexOrThrow("id"))
                val username = getString(getColumnIndexOrThrow("username"))
                val location = getString(getColumnIndexOrThrow("location"))
                val company = getString(getColumnIndexOrThrow("company"))
                val followers = getString(getColumnIndexOrThrow("follower"))
                val following = getString(getColumnIndexOrThrow("following"))
                val repositories = getString(getColumnIndexOrThrow("repository"))
                val avatar = getString(getColumnIndexOrThrow("avatar"))
                val link = getString(getColumnIndexOrThrow("link"))
                val idUser = getInt(getColumnIndexOrThrow("idUser"))
                favUsers.add(User(id, username, name, avatar, location, company, repositories, followers, following, link, idUser))
            }
        }

        Log.d("CONVET_CURSOR", favUsers.toString())

        return favUsers
    }
}
