package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.AuthLoginResponse
import com.betrybe.trybnb.data.models.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthLoginApi {
    @POST("auth")
    suspend fun login(@Body credentials: LoginRequest): Response<AuthLoginResponse>
    // loginRequest sao os dados de login
    // authLoginResponse é a resposta da requisiçao, um token
}