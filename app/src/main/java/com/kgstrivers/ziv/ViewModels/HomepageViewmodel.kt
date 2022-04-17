package com.kgstrivers.ziv.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kgstrivers.ziv.Activities.MainActivity
import com.kgstrivers.ziv.Model.Product
import com.kgstrivers.ziv.Model.Products
import com.kgstrivers.ziv.NetworkInterface.networkInterface
import com.kgstrivers.ziv.RetroInstance.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomepageViewmodel:ViewModel() {

    lateinit var listdata :MutableLiveData<Products>

    init{
        listdata = MutableLiveData()
    }


    fun getProductsLiveDataobserver():MutableLiveData<Products>{
        return listdata
    }

    fun getProductList()
    {
        makeretrofit()
    }

    private fun makeretrofit() {
        val retrofitinstance = RetrofitInstance.getretroInstance().create(networkInterface::class.java)

        val allproduct = retrofitinstance.getAllProducts()

        allproduct.enqueue( object: Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                val resbody = response.body()!!


                listdata.postValue(resbody)
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {

               System.out.println("Failue");
            }
        })
    }
}