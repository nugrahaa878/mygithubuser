package com.nugrahaa.mygithubuser

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nugrahaa.mygithubuser.adapter.ListUserAdapter
import com.nugrahaa.mygithubuser.model.GithubUser
import com.nugrahaa.mygithubuser.model.ResponseUser
import com.nugrahaa.mygithubuser.network.ApiConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var rvGithubUser: RecyclerView
    private var githubUsers = arrayListOf<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGithubUser = rv_user
        rvGithubUser.setHasFixedSize(true)

        addItemApi()
    }

    private fun showRecyclerList() {
        rvGithubUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(githubUsers)
        rvGithubUser.adapter = listUserAdapter
    }

    private fun addItemApi() {
        val client = ApiConfig.getApiService().getListByName("nugrahaa")
        progressBar.visibility = View.VISIBLE
        client.enqueue(object : Callback<ResponseUser> {
            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Gagal gan " + t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                try {
                    val dataArray = response.body()?.items as ArrayList<GithubUser>
                    for (data in dataArray) {
                        githubUsers.add(data)
                    }
                    progressBar.visibility = View.INVISIBLE
                    showRecyclerList()
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
