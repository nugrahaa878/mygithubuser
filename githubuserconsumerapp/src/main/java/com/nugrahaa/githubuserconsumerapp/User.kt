package com.nugrahaa.githubuserconsumerapp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable