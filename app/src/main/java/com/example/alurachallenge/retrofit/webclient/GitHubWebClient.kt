package com.example.alurachallenge.retrofit.webclient

import android.util.Log
import com.example.alurachallenge.retrofit.RetrofitConfig
import com.example.alurachallenge.retrofit.services.ProfileService
import kotlinx.coroutines.flow.flow

class GitHubWebClient(
    private val service: ProfileService = RetrofitConfig().profileService()
) {
    fun findProfileByUsername(user: String) = flow {
        try {
            emit(service.list(user))
        } catch (exception: Exception) {
            Log.e("GitHubWebClient", "error finding user")
        }
    }
}