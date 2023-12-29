package com.example.suitmediatest.repository

import com.example.suitmediatest.api.ApiClient
import com.example.suitmediatest.model.Data
import com.example.suitmediatest.model.User
import retrofit2.Response

class UserRepository {
    suspend fun getUsers(page: Int, perPage: Int): Response<Data>{
        return ApiClient.apiService.getUsers(page, perPage)
    }
}