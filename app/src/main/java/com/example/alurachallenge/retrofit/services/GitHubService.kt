package com.example.alurachallenge.retrofit.services

import com.example.alurachallenge.models.Profile
import com.example.alurachallenge.models.Repository
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}")
    suspend fun findUserByUsername(@Path("user") user: String): Profile

    @GET("/users/{user}/repos")
    suspend fun findRepositoriesByUsername(@Path("user") user: String): List<Repository>
}