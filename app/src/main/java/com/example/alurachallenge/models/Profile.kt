package com.example.alurachallenge.models

import com.example.alurachallenge.ui.screen.ProfileUiState
import com.google.gson.annotations.SerializedName

data class Profile(
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String,
    val name: String,
    val bio: String
)

fun Profile.toProfileUiState(): ProfileUiState {
    return ProfileUiState(
        user = login,
        image = avatar,
        name = name,
        bio = bio
    )
}
