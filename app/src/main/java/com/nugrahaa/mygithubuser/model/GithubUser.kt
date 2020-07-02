package com.nugrahaa.mygithubuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser (
    var username: String?,
    var name: String?,
    var avatar: Int?,
    var location: String?,
    var company: String?,
    var repository: String?,
    var follower: String?,
    var following: String?,
    var link: String?
): Parcelable
