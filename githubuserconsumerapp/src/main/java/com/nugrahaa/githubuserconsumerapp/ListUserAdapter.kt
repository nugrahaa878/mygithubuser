package com.nugrahaa.githubuserconsumerapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_user.view.*

class ListUserAdapter(private val listUser: ArrayList<GithubUser>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(75, 75))
            .into(holder.imgPhoto)

        holder.tvUserId.text = user.idUser.toString()
        holder.tvName.text = user.username
        holder.tvUsername.text = user.username
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.tv_name
        var tvUsername: TextView = itemView.tv_username
        var tvUserId: TextView = itemView.tv_userid
        var imgPhoto: ImageView = itemView.img_item_photo
    }
}