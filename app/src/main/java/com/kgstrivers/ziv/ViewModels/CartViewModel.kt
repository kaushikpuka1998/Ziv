package com.kgstrivers.ziv.ViewModels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kgstrivers.ziv.Activities.CartActivity
import com.kgstrivers.ziv.Activities.ContentActivity
import com.kgstrivers.ziv.Model.CartProduct
import com.kgstrivers.ziv.Model.Products
import com.kgstrivers.ziv.RoomDatabase.Cartprod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.AccessController.getContext

class CartViewModel : ViewModel()  {

    lateinit var listdata : MutableLiveData<List<CartProduct>>
    var sum = 0.0
    init{
        listdata = MutableLiveData()
    }


    fun getCartProductsLiveDataobserver(): MutableLiveData<List<CartProduct>> {
        return listdata
    }

    fun getProductList(context: Context)
    {
        GlobalScope.launch(Dispatchers.IO) {
            makeDatabasecall(context)
        }

    }
    suspend fun makeDatabasecall(context:Context)
    {
            val productDao = Cartprod.getInstance(context).prodDao()
            val list = productDao?.display()
            listdata.postValue(list)
    }

    suspend fun deleteProductList(context: Context,cartProduct: CartProduct) {
        val productDao = Cartprod.getInstance(context).prodDao()
        productDao?.delete(cartProduct)

    }


}

