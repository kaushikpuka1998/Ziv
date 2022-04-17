package com.kgstrivers.ziv

import retrofit2.Call
import retrofit2.http.GET

interface networkInterface {


    @GET("assignment/db")
    fun getAllProducts() : Call<Products>
}