package com.betrybe.trybnb.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitApi {
    val instanceApi: AuthLoginApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restful-booker.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(AuthLoginApi::class.java)
    }
}
