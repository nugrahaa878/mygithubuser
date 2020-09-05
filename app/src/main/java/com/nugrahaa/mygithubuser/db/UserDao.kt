package com.nugrahaa.mygithubuser.db

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY username ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE username = :username")
    fun getByUserName(username: String): LiveData<List<User>>

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user_table WHERE username = :username")
    suspend fun deleteByUserName(username: String)

    // Cursor
    @Query("SELECT * FROM user_table")
    fun cursorgetAll(): Cursor
}