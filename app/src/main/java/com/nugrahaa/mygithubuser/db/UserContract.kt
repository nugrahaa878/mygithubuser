package com.nugrahaa.mygithubuser.db

import android.provider.BaseColumns

internal class UserContract {

    internal class UserColumns: BaseColumns {
        companion object {
            const val TABLE_NAME = "user"
            const val _ID = "_id"
            const val USERNAME = "username"
            const val NAME = "name"
            const val AVATAR = "avatar"
            const val LOCATION = "location"
        }
    }

}