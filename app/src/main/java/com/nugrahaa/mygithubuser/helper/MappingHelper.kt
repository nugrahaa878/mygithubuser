package com.nugrahaa.mygithubuser.helper

import android.database.Cursor
import com.nugrahaa.mygithubuser.db.UserContract
import com.nugrahaa.mygithubuser.model.GithubUser

object MappingHelper {
    fun mapCursorToArrayList(userCursor: Cursor?): ArrayList<GithubUser> {
        val userList = ArrayList<GithubUser>()

        userCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(UserContract.UserColumns._ID))
                val username = getString(getColumnIndexOrThrow(UserContract.UserColumns.USERNAME))
                val name = getString(getColumnIndexOrThrow(UserContract.UserColumns.NAME))
                val avatar = getString(getColumnIndexOrThrow(UserContract.UserColumns.AVATAR))
                val location = getString(getColumnIndexOrThrow(UserContract.UserColumns.LOCATION))
                val company = getString(getColumnIndexOrThrow(UserContract.UserColumns.COMPANY))
                val repository = getString(getColumnIndexOrThrow(UserContract.UserColumns.REPOSITORY))
                val follower = getString(getColumnIndexOrThrow(UserContract.UserColumns.FOLLOWER))
                val following = getString(getColumnIndexOrThrow(UserContract.UserColumns.FOLLOWING))
                val link = getString(getColumnIndexOrThrow(UserContract.UserColumns.LINK))

                userList.add(GithubUser(username, name, avatar, location, company, repository, follower, following, link, id))
            }
        }
        return userList
    }
}