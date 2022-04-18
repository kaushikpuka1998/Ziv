package com.kgstrivers.ziv.RycleviewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kgstrivers.ziv.Model.CartProduct
import com.kgstrivers.ziv.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.singlecartitem.view.*

class CartPageRecyclerViewAdapter: RecyclerView.Adapter<CartPageRecyclerViewAdapter.MyCartViewHolder>() {


    var productslist = mutableListOf<CartProduct>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCartViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.singlecartitem,parent,false)
        return CartPageRecyclerViewAdapter.MyCartViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyCartViewHolder, position: Int) {
        holder.bind(productslist[position])
    }

    override fun getItemCount(): Int {
        return productslist.size
    }

    class  MyCartViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val image = view.cartsingleimage
        val name = view.cartitemname
        val price = view.cartprice
        val rate = view.cartratebar
        val star = rate.progressDrawable

        fun bind(data: CartProduct)
        {
            Picasso.get().load(data.image_url).resize(250,250).centerCrop().into(image)
            name.text = data.name
            price.text = "₹"+data.price
            rate.setRating((data.rating!!.toFloat()))
            rate.setIsIndicator(true)
        }
    }



}