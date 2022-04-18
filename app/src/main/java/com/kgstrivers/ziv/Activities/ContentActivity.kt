package com.kgstrivers.ziv.Activities

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.PointerIcon.load
import android.view.View
import android.widget.Toast
import android.widget.ViewAnimator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.kgstrivers.ziv.BuildConfig.DEBUG
import com.kgstrivers.ziv.Model.CartProduct
import com.kgstrivers.ziv.Model.Product
import com.kgstrivers.ziv.R
import com.kgstrivers.ziv.RoomDatabase.Cartprod
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.singleelementrecyclerview.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        val intent = getIntent()

        var imgurl = intent.getStringExtra("imgurl")
        var name = intent.getStringExtra("name")
        var price = intent.getStringExtra("price")
        var b = intent.getExtras()

        val rating = b?.getInt("rating")


        Picasso.get().load(imgurl).resize(490,580).centerCrop().into(contentimage)
//        Picasso.get().load(imgurl).fit().resize(720,480).centerCrop().into(contentimage);
        //Glide.with(this).load(imgurl).apply( RequestOptions().override(720, 480)).into(contentimage);
        contentprice.text = "₹"+price
        contentname.text = name

        if(rating!=null)
        {
            contentrating.setRating((rating.toFloat()))
        }
        contentrating.setIsIndicator(true)



        addtocart.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {

                val cartsingleproduct = CartProduct(null,imgurl.toString(),name.toString(),price.toString(),rating)

               GlobalScope.launch(Dispatchers.IO) {

                   Cartprod.getInstance(this@ContentActivity).prodDao().insert(cartsingleproduct)

               }
                Toast.makeText(this@ContentActivity,"Product Added into the Cart",Toast.LENGTH_SHORT).show()

               Log.d("Success MSG","Data inserted into Room " +this@ContentActivity)

                val b = Intent(this@ContentActivity,CartActivity::class.java)
                startActivity(b)

            }
        })

        //Toast.makeText(this,rating,Toast.LENGTH_SHORT).show()


    }
}