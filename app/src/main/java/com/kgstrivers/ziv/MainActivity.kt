package com.kgstrivers.ziv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        makeretrofit();
    }

    private fun makeretrofit() {
        val retrofitinstance = RetrofitInstance.getretroInstance().create(networkInterface::class.java)

        val allproduct = retrofitinstance.getAllProducts()

        allproduct.enqueue( object: Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                val resbody = response.body()!!

               for(item in resbody.products)
               {
                   System.out.println(item);

               }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {

            }
        })
    }
}