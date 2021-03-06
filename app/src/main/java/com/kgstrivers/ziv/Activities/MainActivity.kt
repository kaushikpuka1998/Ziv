package com.kgstrivers.ziv.Activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kgstrivers.ziv.Model.Products
import com.kgstrivers.ziv.R
import com.kgstrivers.ziv.RoomDatabase.Cartprod
import com.kgstrivers.ziv.RycleviewAdapters.HomePageRecyclerviewAdapter
import com.kgstrivers.ziv.ViewModels.HomepageViewmodel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.customtoolbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var homeadapter :HomePageRecyclerviewAdapter
    lateinit var homepageViewmodel: HomepageViewmodel

    private var backPressedtime: Long = 0
    override fun onBackPressed() { //Double Back Pressed  and Exit Function
        if (backPressedtime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            val intnt = Intent(Intent.ACTION_MAIN)
            intnt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intnt.addCategory(Intent.CATEGORY_HOME)
            startActivity(intnt)

        } else {
            Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show()
        }
        backPressedtime = System.currentTimeMillis()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initiaterecycleview()
        initiateViewmodel()
        calldata()

        toolbarcart.setOnClickListener(object: View.OnClickListener{

            override fun onClick(p0: View?) {
                val r = Intent(applicationContext,CartActivity::class.java)
                startActivity(r)
            }
        })


    }

    private fun initiaterecycleview()
    {


        recycleview.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decor = DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL)

            getDrawable(R.drawable.rounded!!)?.let { decor.setDrawable(it) }

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
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.customeprogressbar)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.drawable))
        dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        dialog.show()
       homepageViewmodel =  ViewModelProvider(this).get(HomepageViewmodel::class.java)
        homepageViewmodel.getProductsLiveDataobserver().observe(this, Observer<Products> {
            if(it!=null)
            {
               homeadapter.productslist = it.products.toMutableList()
                dialog.dismiss()
                homeadapter.notifyDataSetChanged()
            }
            else
            {
                Toast.makeText(this,"No Result",Toast.LENGTH_SHORT).show()
            }
        })
    }

}