package com.nugrahaa.mygithubuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nugrahaa.mygithubuser.R
import com.nugrahaa.mygithubuser.activity.DetailUserActivity
import com.nugrahaa.mygithubuser.db.User
import kotlinx.android.synthetic.main.item_row_user.view.*

class FavoriteUserAdapter: RecyclerView.Adapter<FavoriteUserAdapter.ListViewHolder>() {

    private var listUser = emptyList<User>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteUserAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteUserAdapter.ListViewHolder, position: Int) {
        val user = listUser[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(75, 75))
            .into(holder.imgPhoto)

        holder.tvUserId.text = user.idUser.toString()
        holder.tvName.text = user.username
        holder.tvUsername.text = user.username

        val mContext = holder.itemView.context
        holder.itemView.setOnClickListener {
            val moveWithUsernameIntent = Intent(mContext, DetailUserActivity::class.java)
            moveWithUsernameIntent.putExtra(DetailUserActivity.EXTRA_USERNAME, user.username)
            mContext.startActivity(moveWithUsernameIntent)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.tv_name
        var tvUsername: TextView = itemView.tv_username
        var tvUserId: TextView = itemView.tv_userid
        var imgPhoto: ImageView = itemView.img_item_photo
    }

    fun setData(listUser: List<User>) {
        this.listUser = listUser
        notifyDataSetChanged()
    }

}