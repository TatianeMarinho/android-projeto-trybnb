package com.betrybe.trybnb.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {
    val instanceApi: ReqApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restful-booker.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ReqApi::class.java)
    }
}
