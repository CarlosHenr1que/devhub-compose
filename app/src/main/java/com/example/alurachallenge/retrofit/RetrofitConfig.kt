package com.example.alurachallenge.retrofit

import com.example.alurachallenge.retrofit.services.GitHubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    private val retrofit = init()

    private fun init(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun profileService(): GitHubService = retrofit.create(GitHubService::class.java)
}