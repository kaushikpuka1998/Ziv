package com.kgstrivers.ziv.RycleviewAdapters

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.kgstrivers.ziv.Activities.ContentActivity
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

    override fun onBindViewHolder(holder:  HomePageRecyclerviewAdapter.MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(productslist[position])

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(holder.itemView.context,ContentActivity::class.java)
                intent.putExtra("name",productslist[position].name)
                intent.putExtra("price",productslist[position].price)
                //intent.putExtra()

                var b = Bundle()
                b.putInt("rating",productslist[position].rating)
                intent.putExtras(b)
                intent.putExtra("imgurl",productslist[position].image_url)


                holder.itemView.context.startActivity(intent)
            }
        })
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
        val star = rate.progressDrawable

        fun bind( data: Product)
        {
            Picasso.get().load(data.image_url).into(image)
            name.text = data.name
            price.text = "₹"+data.price
            rate.setRating((data.rating.toFloat()))
            rate.setIsIndicator(true)
        }
    }


}

