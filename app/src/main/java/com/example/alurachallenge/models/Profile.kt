package com.example.alurachallenge.models

import com.google.gson.annotations.SerializedName

data class Profile(
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String,
    val name: String,
    val bio: String
)
