package com.nugrahaa.mygithubuser.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nugrahaa.mygithubuser.db.User
import com.nugrahaa.mygithubuser.db.UserDatabase
import com.nugrahaa.mygithubuser.db.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application){

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun deleteByUserName(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserByName(username)
        }
    }

}