package com.nugrahaa.mygithubuser.db

import androidx.lifecycle.LiveData

class UserRepository(private var userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

}