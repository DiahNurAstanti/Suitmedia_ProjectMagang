package com.example.suitmediatest.api

import com.example.suitmediatest.model.Data
import com.example.suitmediatest.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : Response<Data>
}