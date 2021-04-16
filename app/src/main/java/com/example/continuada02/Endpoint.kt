package com.example.continuada02

import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("bandtec-api/cachorros")
    fun getCachorros() : Call<List<Cachorros>>

}