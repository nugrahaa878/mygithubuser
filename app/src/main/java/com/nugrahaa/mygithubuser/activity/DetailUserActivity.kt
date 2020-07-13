package com.nugrahaa.mygithubuser.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.mygithubuser.R
import com.nugrahaa.mygithubuser.adapter.SectionsPagerAdapter
import com.nugrahaa.mygithubuser.model.GithubUser
import kotlinx.android.synthetic.main.activity_detail_user.*
import kotlinx.android.synthetic.main.item_row_user.*

class DetailUserActivity : AppCompatActivity() {

    private lateinit var imgPhoto: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvCompany: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvRepository: TextView
    private lateinit var tvFollower: TextView
    private lateinit var tvFollowing: TextView
    private lateinit var btnGithub: Button

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        // memunculkan tombol panah back
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // mengubah title app bar
        supportActionBar?.title = "About"

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f

        prepare()
        add()
    }

    private fun add() {
        var person = intent.getParcelableExtra(EXTRA_PERSON) as GithubUser

        Glide.with(this)
            .load(person.avatar)
            .apply(RequestOptions())
            .into(imgPhoto)
        tvName.text = person.name
        tvUsername.text = person.username
        tvCompany.text = person.company
        tvLocation.text = person.location
        tvRepository.text = person.repository
        tvFollower.text = person.follower
        tvFollowing.text = person.following

        btnGithub.setOnClickListener{
            val linkGithub = person.link
            val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkGithub))
            startActivity(githubIntent)
        }
    }

    private fun prepare() {
        imgPhoto = img_user_photo
        tvName = tv_name_detail
        tvUsername = tv_username_detail
        tvCompany = tv_company_detail
        tvLocation = tv_location_detail
        tvRepository = tv_repository_detail
        tvFollower = tv_follower_detail
        tvFollowing = tv_following_detail
        btnGithub = btn_github
    }
}
