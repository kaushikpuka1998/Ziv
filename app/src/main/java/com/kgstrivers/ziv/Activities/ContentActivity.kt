package com.kgstrivers.ziv.Activities

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.kgstrivers.ziv.Model.Product
import com.kgstrivers.ziv.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_content.*

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

        //Picasso.get().load(imgurl).fit().resize(800,300).centerCrop().into(contentimage);
        Glide.with(this).load(imgurl).apply( RequestOptions().override(720, 480)).into(contentimage);
        contentprice.text = "₹"+price
        contentname.text = name

        if(rating!=null)
        {
            contentrating.setRating((rating.toFloat()))
        }
//
        contentrating.setIsIndicator(true)

        //Toast.makeText(this,rating,Toast.LENGTH_SHORT).show()


    }
}