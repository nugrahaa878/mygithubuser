package com.nugrahaa.mygithubuser

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nugrahaa.mygithubuser.activity.HomeActivity
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

    private var TIME = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeIntent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(homeIntent)
    }


}
