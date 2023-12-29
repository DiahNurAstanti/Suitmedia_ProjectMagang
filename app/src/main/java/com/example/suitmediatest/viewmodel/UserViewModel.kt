package com.example.suitmediatest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediatest.model.Data
import com.example.suitmediatest.model.User
import com.example.suitmediatest.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(val repository: UserRepository): ViewModel() {
    private val _users = MutableLiveData<Response<Data>>()

    val users : LiveData<Response<Data>>
        get() = _users

    fun getUsers(page: Int, perPage: Int) {
        viewModelScope.launch {
            val response = repository.getUsers(page, perPage)
            _users.value = response
        }
    }
}