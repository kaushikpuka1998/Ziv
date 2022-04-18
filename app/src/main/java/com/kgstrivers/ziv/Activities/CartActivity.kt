package com.kgstrivers.ziv.Activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kgstrivers.ziv.Callbacks.SwipeGesture
import com.kgstrivers.ziv.Model.CartProduct
import com.kgstrivers.ziv.R
import com.kgstrivers.ziv.RycleviewAdapters.CartPageRecyclerViewAdapter
import com.kgstrivers.ziv.ViewModels.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CartActivity : AppCompatActivity() {

    lateinit var cartadapter :CartPageRecyclerViewAdapter
    lateinit var cartpageViewmodel: CartViewModel

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


//
            val swipegesture = object: SwipeGesture()
            {
                @SuppressLint("LongLogTag")
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when(direction)
                    {
                        ItemTouchHelper.RIGHT->{

                        GlobalScope.launch(Dispatchers.IO) {


                            var willbedeleted = cartadapter.returnproduct(viewHolder.adapterPosition)

                            deletedata(this@CartActivity, willbedeleted)

                        }

                        }
                    }

                }
            }
            val touchHelper = ItemTouchHelper(swipegesture)
            touchHelper.attachToRecyclerView(cartrecycleview)






        }

    }

    private fun initiateViewmodel()
    {
        var sum = 0.0
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

    private suspend fun deletedata( context: Context,cartProduct: CartProduct)
    {
        cartpageViewmodel.deleteProductList(context,cartProduct)
        calldata(context)



    }


}


