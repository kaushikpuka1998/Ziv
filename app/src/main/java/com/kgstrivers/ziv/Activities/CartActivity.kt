package com.kgstrivers.ziv.Activities

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kgstrivers.ziv.Model.CartProduct
import com.kgstrivers.ziv.Model.Products
import com.kgstrivers.ziv.R
import com.kgstrivers.ziv.RycleviewAdapters.CartPageRecyclerViewAdapter
import com.kgstrivers.ziv.RycleviewAdapters.HomePageRecyclerviewAdapter
import com.kgstrivers.ziv.ViewModels.CartViewModel
import com.kgstrivers.ziv.ViewModels.HomepageViewmodel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round

class CartActivity : AppCompatActivity() {

    lateinit var cartadapter :CartPageRecyclerViewAdapter
    lateinit var cartpageViewmodel: CartViewModel

    var sum = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initiaterecycleview()

        initiateViewmodel()

        calldata(this)

    }

    private fun initiaterecycleview()
    {
        cartrecycleview.apply{
            layoutManager = LinearLayoutManager(this@CartActivity)
            val decor = DividerItemDecoration(this@CartActivity, DividerItemDecoration.VERTICAL)
            getDrawable(R.drawable.rounded!!)?.let { decor.setDrawable(it) }

            addItemDecoration(decor)

            cartadapter =CartPageRecyclerViewAdapter()
            adapter = cartadapter

        }
    }

    private fun initiateViewmodel()
    {
        cartpageViewmodel =  ViewModelProvider(this).get(CartViewModel::class.java)
        cartpageViewmodel.getCartProductsLiveDataobserver().observe( this,{

            if(it!=null)
            {
                cartadapter.productslist = it as MutableList<CartProduct>

                for(item in cartadapter.productslist)
                {
                    sum+= item.price!!.toFloat()
                }
               totalprice.text = sum.toString()
                cartadapter.notifyDataSetChanged()

            }
            else
            {
                Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun calldata(context:Context)
    {
        cartpageViewmodel.getProductList(context)
    }


    private fun sumprice(context: Context)
    {

    }
}

