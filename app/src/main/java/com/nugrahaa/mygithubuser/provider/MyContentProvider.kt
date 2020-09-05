package com.nugrahaa.mygithubuser.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.nugrahaa.mygithubuser.db.UserDao
import com.nugrahaa.mygithubuser.db.UserDatabase
import java.lang.UnsupportedOperationException

class MyContentProvider : ContentProvider() {

    companion object {
        private const val USER = 1
        private const val AUTHORITY = "com.nugrahaa.mygithubuser"
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "user_table", USER)
        }
    }

    private val userDao: UserDao by lazy {
        UserDatabase.getDatabase(requireNotNull(context)).userDao()
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        throw UnsupportedOperationException()
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException()
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException()
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when (sUriMatcher.match(uri)) {
            USER -> userDao.cursorgetAll()
            else -> null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        throw UnsupportedOperationException()
    }
}
