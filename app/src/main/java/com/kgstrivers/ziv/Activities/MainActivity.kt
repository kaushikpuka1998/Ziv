package com.kgstrivers.ziv.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kgstrivers.ziv.Model.Product
import com.kgstrivers.ziv.Model.Products
import com.kgstrivers.ziv.R
import com.kgstrivers.ziv.RetroInstance.RetrofitInstance
import com.kgstrivers.ziv.NetworkInterface.networkInterface
import com.kgstrivers.ziv.RycleviewAdapters.HomePageRecyclerviewAdapter
import com.kgstrivers.ziv.ViewModels.HomepageViewmodel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var homeadapter :HomePageRecyclerviewAdapter
    lateinit var homepageViewmodel: HomepageViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initiaterecycleview()
        initiateViewmodel()
        calldata()


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

    private fun calldata()
    {
        homepageViewmodel.getProductList()
    }

    private fun initiateViewmodel()
    {
       homepageViewmodel =  ViewModelProvider(this).get(HomepageViewmodel::class.java)
        homepageViewmodel.getProductsLiveDataobserver().observe(this, Observer<Products> {
            if(it!=null)
            {
               homeadapter.productslist = it.products.toMutableList()
                homeadapter.notifyDataSetChanged()
            }
            else
            {
                Toast.makeText(this,"No Result",Toast.LENGTH_SHORT).show()
            }
        })
    }

}