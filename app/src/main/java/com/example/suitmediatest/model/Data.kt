package com.example.suitmediatest.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("data")
    val users: List<User>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)