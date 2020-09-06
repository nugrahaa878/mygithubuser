package com.nugrahaa.mygithubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nugrahaa.mygithubuser.activity.HomeActivity
import com.nugrahaa.mygithubuser.activity.SplashActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splashIntent = Intent(this@MainActivity, SplashActivity::class.java)
        startActivity(splashIntent)
    }

}
