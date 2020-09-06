package com.nugrahaa.mygithubuser.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nugrahaa.mygithubuser.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val time = 2500
        val homeIntent = Intent(this@SplashActivity, HomeActivity::class.java)
        Handler().postDelayed({
            startActivity(homeIntent)
            finish()
        }, time.toLong())

    }
}