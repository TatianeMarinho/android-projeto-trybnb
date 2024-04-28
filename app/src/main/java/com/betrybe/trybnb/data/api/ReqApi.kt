package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.AuthLoginResponse
import com.betrybe.trybnb.data.models.BookingId
import com.betrybe.trybnb.data.models.DataBooking
import com.betrybe.trybnb.data.models.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ReqApi {
    @POST("auth")
    suspend fun login(@Body credentials: LoginRequest): Response<AuthLoginResponse>
    // loginRequest sao os dados de login
    // authLoginResponse é a resposta da requisiçao, um token
    @GET("booking")
    suspend fun getBookings(): Response<List<BookingId>>

    @GET("booking/{id}")
    suspend fun getBookingId(
        @Path("id") id: String,
        @Header("Accept") accept: String = "*/*"
    ): Response<DataBooking>
}