package com.nugrahaa.mygithubuser.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.mygithubuser.R
import com.nugrahaa.mygithubuser.activity.DetailUserActivity
import com.nugrahaa.mygithubuser.model.GithubUser
import kotlinx.android.synthetic.main.item_row_favorite.view.*

class FavoriteUserAdapter(private val activity: Activity) : RecyclerView.Adapter<FavoriteUserAdapter.FavoriteUserViewHolder>(){

    var listUser = ArrayList<GithubUser>()
        set(listUser) {
            if (listUser.size > 0) {
                this.listUser.clear()
            }
            this.listUser.addAll(listUser)

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteUserAdapter.FavoriteUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_favorite, parent, false)
        return FavoriteUserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listUser.size
    }

    override fun onBindViewHolder(holder: FavoriteUserAdapter.FavoriteUserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    inner class FavoriteUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(githubUser: GithubUser) {
            with(itemView) {
                tv_favorite_name.text = githubUser.name
                tv_favorite_location.text = githubUser.location
            }
        }
    }

    fun addItem(githubUser: GithubUser) {
        this.listUser.add(githubUser)
        notifyItemInserted(this.listUser.size - 1)
    }

    fun updateItem(position: Int, githubUser: GithubUser) {
        this.listUser[position] = githubUser
        notifyItemChanged(position, githubUser)
    }

    fun removeItem(position: Int) {
        this.listUser.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listUser.size)
    }
}