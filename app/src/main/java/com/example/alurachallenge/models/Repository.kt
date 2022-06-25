package com.example.alurachallenge.models

class Repository(
    val name: String = "",
    val description: String = ""
)

fun Repository.toGitHubRepository() = Repository(
    name = name,
    description = description ?: ""
)
