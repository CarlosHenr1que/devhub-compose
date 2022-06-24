package com.example.alurachallenge.retrofit.services

import com.example.alurachallenge.models.Profile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {
    @GET("users/{user}")
    fun list(@Path("user") user: String): Call<Profile>
}