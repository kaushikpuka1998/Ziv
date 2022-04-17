package com.kgstrivers.ziv.NetworkInterface

import com.kgstrivers.ziv.Model.Products
import retrofit2.Call
import retrofit2.http.GET

interface networkInterface {


    @GET("assignment/db")
    fun getAllProducts() : Call<Products>
}