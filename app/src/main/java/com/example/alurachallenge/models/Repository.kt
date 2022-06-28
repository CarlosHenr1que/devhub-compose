package com.example.alurachallenge.models

import com.google.gson.annotations.SerializedName

class Repository(
    val name: String = "",
    val description: String = "",
    @SerializedName("stargazers_count")
    val stars: String = "",
    val language: String = ""
)

fun Repository.toGitHubRepository() = Repository(
    name = name,
    description = description ?: "",
    stars = stars,
    language = language ?: ""
)
