package com.example.alurachallenge.retrofit.webclient

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.alurachallenge.models.toGitHubRepository
import com.example.alurachallenge.models.toProfileUiState
import com.example.alurachallenge.retrofit.RetrofitConfig
import com.example.alurachallenge.retrofit.services.GitHubService
import com.example.alurachallenge.ui.screen.ProfileUiState

class GitHubWebClient(
    private val service: GitHubService = RetrofitConfig().profileService()
) {
    var uiState by mutableStateOf(ProfileUiState())
        private set

    suspend fun findProfileByUsername(user: String) {
        try {
            val profile = service.findUserByUsername(user).toProfileUiState()
            val repositories = service.findRepositoriesByUsername(user).map { it.toGitHubRepository() }
            uiState = profile.copy(repositories = repositories)
        } catch (exception: Exception) {
            Log.e("GitHubWebClient", "error finding user $exception")
        }
    }
}