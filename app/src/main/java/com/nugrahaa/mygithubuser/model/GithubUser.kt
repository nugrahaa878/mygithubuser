package com.nugrahaa.mygithubuser.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser (
    @field:SerializedName("login")
    var username: String?,

    @field:SerializedName("name")
    var name: String?,

    @field:SerializedName("avatar_url")
    var avatar: String?,

    @field:SerializedName("location")
    var location: String?,

    @field:SerializedName("company")
    var company: String?,

    @field:SerializedName("public_repos")
    var repository: String?,

    @field:SerializedName("followers")
    var follower: String?,

    @field:SerializedName("following")
    var following: String?

): Parcelable
