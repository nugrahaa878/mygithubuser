package com.nugrahaa.mygithubuser

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nugrahaa.mygithubuser.adapter.ListUserAdapter
import com.nugrahaa.mygithubuser.model.GithubUser
import com.nugrahaa.mygithubuser.network.ApiConfig
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvGithubUser: RecyclerView
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataLink: Array<String>
    private lateinit var dataAvatar: TypedArray

    private var githubUsers = arrayListOf<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGithubUser = rv_user
        rvGithubUser.setHasFixedSize(true)

        prepare()
        addItem()
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvGithubUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(githubUsers)
        rvGithubUser.adapter = listUserAdapter
    }

    private fun addItem() {
        for (position in dataName.indices) {
                val user = GithubUser(
                    dataUsername[position],
                    dataName[position],
                    dataAvatar.getResourceId(position, -1),
                    dataLocation[position],
                    dataCompany[position],
                    dataRepository[position],
                    dataFollower[position],
                    dataFollowing[position]
                )
            githubUsers.add(user)
        }
    }

    private fun addItemApi() {
        val client = ApiConfig.getApiService().getListByName("ariangga")

    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataLocation = resources.getStringArray(R.array.location)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
        dataCompany = resources.getStringArray(R.array.company)
        dataRepository = resources.getStringArray(R.array.repository)
        dataFollower = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataLink = resources.getStringArray(R.array.link)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
