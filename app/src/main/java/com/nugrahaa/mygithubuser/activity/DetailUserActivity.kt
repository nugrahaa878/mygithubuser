package com.nugrahaa.mygithubuser.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.mygithubuser.R
import com.nugrahaa.mygithubuser.adapter.SectionsPagerAdapter
import com.nugrahaa.mygithubuser.db.User
import com.nugrahaa.mygithubuser.db.UserDatabase
import com.nugrahaa.mygithubuser.viewmodel.UserViewModel
import com.nugrahaa.mygithubuser.model.GithubUser
import com.nugrahaa.mygithubuser.network.ApiConfig
import kotlinx.android.synthetic.main.activity_detail_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DetailUserActivity : AppCompatActivity() {

    private lateinit var imgPhoto: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvCompany: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvRepository: TextView
    private lateinit var tvFollower: TextView
    private lateinit var tvFollowing: TextView
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    private lateinit var dataFavorite: GithubUser
    private var isFav: Boolean = false

    private lateinit var mUserViewModel: UserViewModel

    companion object {
        private const val TAG = "DetailUserActivity"
        const val EXTRA_USERNAME = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        // memunculkan tombol panah back
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // mengubah title app bar
        supportActionBar?.title = resources.getString(R.string.about_me)

        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f

        prepare()
        val username = intent.getStringExtra(EXTRA_USERNAME)
        addDetailUserApi(username)
        sectionsPagerAdapter.username = username

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Favorite logic
        floating_favorite.setOnClickListener {
            try {
                checkLiked()
                insertDataToDatabase()
            } catch (e: Exception) {
                Toast.makeText(this, "Harap tunggu loading data terlebih dahulu", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun checkLiked() {
        val db = UserDatabase.getDatabase(applicationContext)
        val dao = db.userDao()

        dao.getByUserName(dataFavorite.username.toString()).observe(this, Observer { liveUserData ->
            if (liveUserData.isNotEmpty() && liveUserData[0].username != null) {
                isFav = true
                changeFabIcon(isFav)
            } else {
                isFav = false
                changeFabIcon(isFav)
            }
        })
    }

    private fun changeFabIcon(fav: Boolean) {
        if (fav) {
            floating_favorite.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.heart_pink))
        } else {
            floating_favorite.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.heart_black_2))
        }
    }

    private fun insertDataToDatabase() {
        val username = dataFavorite.username
        val name = dataFavorite.name
        val avatar = dataFavorite.avatar
        val location = dataFavorite.location
        val company = dataFavorite.company
        val repository = dataFavorite.repository
        val follower = dataFavorite.follower
        val following = dataFavorite.following
        val link = dataFavorite.link
        val userId = dataFavorite.idUser

        val user = User(0, username.toString(), name, avatar, location, company, repository, follower, following, link, userId)

        if (!isFav) {
            mUserViewModel.addUser(user)
            Toast.makeText(this, "Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        } else {
            mUserViewModel.deleteByUserName(user.username.toString())
            Toast.makeText(this, "Dihapus dari favorit", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addDetailUserApi(username: String?) {
        val client = username?.let {
            ApiConfig.getApiService().getUserDetail("token 3665e72584dd61f52b327a2eede7e07ee188fefa",
                it
            )
        }
        client?.enqueue(object : Callback<GithubUser> {
            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                Toast.makeText(this@DetailUserActivity, "Failed in DetailUserActivity", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                try {
                    val data = response.body()
                    if (data != null) {
                        dataFavorite = data
                    }
                    Log.d(TAG, "Hasil pencarian ${data?.username}")
                    progress_bar_detail.visibility = View.VISIBLE

                    Glide.with(this@DetailUserActivity)
                        .load(data?.avatar)
                        .apply(RequestOptions())
                        .into(imgPhoto)
                    tvName.text = if (data?.name != null) data.name else "-"
                    tvUsername.text = if (data?.username != null) data.username else "-"
                    tvCompany.text = if (data?.company != null) data.company else "-"
                    tvLocation.text = if (data?.location != null) data.location else "-"
                    tvRepository.text = if (data?.repository != null) data.repository else "-"
                    tvFollower.text = if (data?.follower != null) data.follower else "-"
                    tvFollowing.text = if (data?.following != null) data.following else "-"

                    progress_bar_detail.visibility = View.INVISIBLE

                    checkLiked()
                } catch (e: Exception) {
                    Toast.makeText(this@DetailUserActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

        })
    }

    private fun prepare() {
        imgPhoto = img_user_photo
        tvName = tv_name_detail
        tvUsername = tv_username_detail
        tvCompany = tv_company_detail
        tvLocation = tv_location_detail
        tvRepository = tv_repository_detail
        tvFollower = tv_follower
        tvFollowing = tv_following
    }
}
