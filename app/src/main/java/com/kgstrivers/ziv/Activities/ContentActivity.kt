package com.kgstrivers.ziv.Activities

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.util.Log.DEBUG
import android.view.PointerIcon.load
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.customtoolbar.*
import kotlinx.android.synthetic.main.singleelementrecyclerview.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)


        toolbarcart.setOnClickListener(object: View.OnClickListener{

            override fun onClick(p0: View?) {
                val r = Intent(applicationContext,CartActivity::class.java)
                startActivity(r)
            }
        })
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

                visibilityclosed()
                addtocartanimation.playAnimation()


                val cartsingleproduct = CartProduct(imgurl.toString(),name.toString(),price.toString(),rating)

                val thread2: Thread = object : Thread() {
                    override fun run() {
                        try {
                            sleep(2500)
                            visibilityopened()
                            //addtocartlayout.visibility = View.GONE

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        finally{


                            GlobalScope.launch(Dispatchers.IO) {

                                if(Cartprod.getInstance(this@ContentActivity).prodDao().isproductAdded(cartsingleproduct.image_url) == 1)
                                {
                                    Looper.prepare()
                                    visibilityopened()
                                    Toast.makeText(this@ContentActivity,"Already In Cart",Toast.LENGTH_SHORT).show()
                                    Looper.loop()
                                }
                                else
                                {
                                    Cartprod.getInstance(this@ContentActivity).prodDao().insert(cartsingleproduct)
                                    Looper.prepare()
                                    Log.d("Success MSG","Data inserted into Room " +this@ContentActivity)
                                    val b = Intent(this@ContentActivity,CartActivity::class.java)
                                    startActivity(b)
                                    Toast.makeText(this@ContentActivity,"Product Added into the Cart",Toast.LENGTH_SHORT).show()
                                    Looper.loop()
                                }
                            }
                        }
                    }
                }
                thread2.start()





            }
        })

        //Toast.makeText(this,rating,Toast.LENGTH_SHORT).show()


    }

    fun visibilityclosed()
    {
        contentimage.animate().translationY(1920F).setDuration(500);

        contentrating.animate().translationY(1920F).setDuration(500);

        contentrating.animate().translationY(1920F).setDuration(500);

        contentprice.animate().translationY(1920F).setDuration(500);

        contentprice.animate().translationY(1920F).setDuration(500);

        contentname.animate().translationY(1920F).setDuration(500);

        addtocart.animate().translationY(1920F).setDuration(500);

        addtocartanimation.visibility = View.VISIBLE
    }

    fun visibilityopened()
    {

        contentimage.animate().translationY(0F).setDuration(100);

        contentrating.animate().translationY(0F).setDuration(100);

        contentrating.animate().translationY(0F).setDuration(100);

        contentprice.animate().translationY(0F).setDuration(100);

        contentprice.animate().translationY(0F).setDuration(100);

        contentname.animate().translationY(0F).setDuration(100);

        addtocart.animate().translationY(0F).setDuration(100);

        addtocartanimation.visibility = View.GONE

    }
}