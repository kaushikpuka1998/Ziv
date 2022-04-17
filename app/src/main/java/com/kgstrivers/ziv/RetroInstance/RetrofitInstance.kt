package com.kgstrivers.ziv.RetroInstance

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object
    {
        val baseurl = "https://my-json-server.typicode.com/nancymadan/"
        fun getretroInstance():Retrofit{

            val log = HttpLoggingInterceptor()
            log.level = HttpLoggingInterceptor.Level.BODY
            val client  = OkHttpClient.Builder()
            client.addInterceptor(log)


            return  Retrofit.Builder().
            addConverterFactory(GsonConverterFactory.create()).
            baseUrl(baseurl).client(client.build())
                .build()

        }

    }
}