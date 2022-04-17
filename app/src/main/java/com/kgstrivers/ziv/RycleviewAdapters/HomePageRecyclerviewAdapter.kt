package com.kgstrivers.ziv.RycleviewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kgstrivers.ziv.Model.Product
import com.kgstrivers.ziv.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.singleelementrecyclerview.view.*

class HomePageRecyclerviewAdapter :RecyclerView.Adapter<HomePageRecyclerviewAdapter.MyViewHolder>(){

    var productslist = mutableListOf<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageRecyclerviewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.singleelementrecyclerview,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder:  HomePageRecyclerviewAdapter.MyViewHolder, position: Int) {
        holder.bind(productslist[position])
    }

    override fun getItemCount(): Int {
        return productslist.size
    }


    class  MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {

        val image = view.imageview
        val name = view.name
        val price = view.price
        val rate = view.ratebar
        fun bind( data: Product)
        {
            Picasso.get().load(data.image_url).into(image)
            name.text = data.name
            price.text = "₹"+data.price
            rate.setRating((data.rating.toFloat()))
        }
    }


}
