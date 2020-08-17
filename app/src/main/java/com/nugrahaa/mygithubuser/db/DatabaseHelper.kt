package com.nugrahaa.mygithubuser.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.nugrahaa.mygithubuser.db.UserContract.UserColumns.Companion.TABLE_NAME
import com.nugrahaa.mygithubuser.db.UserContract.UserColumns

internal class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "dbuser"

        private const val DATABASE_VERSION = 1

        private val SQL_CREATE_TABLE_USER = "CREATE TABLE $TABLE_NAME" +
                " (${UserColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${UserColumns.USERNAME} TEXT," +
                " ${UserColumns.NAME} TEXT," +
                " ${UserColumns.AVATAR} TEXT," +
                " ${UserColumns.LOCATION} TEXT)"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}