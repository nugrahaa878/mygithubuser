package com.nugrahaa.mygithubuser.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String?,
    val name: String?,
    val avatar: String?,
    val location: String?,
    val company: String?,
    val repository: String?,
    val follower: String?,
    val following: String?,
    val link: String?,
    val idUser: Int?
)