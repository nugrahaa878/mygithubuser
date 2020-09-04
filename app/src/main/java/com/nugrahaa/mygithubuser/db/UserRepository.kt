package com.nugrahaa.mygithubuser.db

import androidx.lifecycle.LiveData

class UserRepository(private var userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.delete(user)
    }

    suspend fun deleteUserByName(username: String) {
        userDao.deleteByUserName(username)
    }

}