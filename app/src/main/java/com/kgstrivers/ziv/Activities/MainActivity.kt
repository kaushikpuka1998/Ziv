package com.kgstrivers.ziv.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kgstrivers.ziv.Model.Products
import com.kgstrivers.ziv.R
import com.kgstrivers.ziv.RetroInstance.RetrofitInstance
import com.kgstrivers.ziv.NetworkInterface.networkInterface
import com.kgstrivers.ziv.RycleviewAdapters.HomePageRecyclerviewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var homeadapter :HomePageRecyclerviewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        makeretrofit();

        initiaterecycleview()


    }

    private fun initiaterecycleview()
    {
        recycleview.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decor = DividerItemDecoration(this@MainActivity,DividerItemDecoration.HORIZONTAL)

            addItemDecoration(decor)

            homeadapter = HomePageRecyclerviewAdapter()
            adapter = homeadapter

        }
    }
    private fun makeretrofit() {
        val retrofitinstance = RetrofitInstance.getretroInstance().create(networkInterface::class.java)

        val allproduct = retrofitinstance.getAllProducts()

        allproduct.enqueue( object: Callback<Products>{
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                val resbody = response.body()!!

               for(item in resbody.products)
               {
                   System.out.println(item);

               }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {

                Toast.makeText(this@MainActivity,"Error in Fetching",Toast.LENGTH_SHORT).show()
            }
        })
    }
}