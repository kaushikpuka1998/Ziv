package com.kgstrivers.ziv.Activities
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kgstrivers.ziv.Callbacks.SwipeGesture
import com.kgstrivers.ziv.Model.CartProduct
import com.kgstrivers.ziv.R
import com.kgstrivers.ziv.RoomDatabase.Cartprod
import com.kgstrivers.ziv.RycleviewAdapters.CartPageRecyclerViewAdapter
import com.kgstrivers.ziv.ViewModels.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.customedialog.*
import kotlinx.android.synthetic.main.customedialog.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.http.HTTP_GONE


class CartActivity : AppCompatActivity() {

    lateinit var cartadapter :CartPageRecyclerViewAdapter
    lateinit var cartpageViewmodel: CartViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        initiaterecycleview()

        initiateViewmodel()

        calldata(this)


        submitanimation1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                submitanimation1.playAnimation()
            }
        })


        finalplacebutton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                GlobalScope.launch(Dispatchers.IO) {

                    if(cartadapter.getItemCountcheck() == 0)
                    {

                    }
                    else
                    {
                        Cartprod.getInstance(this@CartActivity).prodDao().deleteAll();
                        calldata(this@CartActivity)
                    }

                }

                if(cartadapter.getItemCountcheck() != 0)
                {
                    Handler(Looper.getMainLooper()).postDelayed({

                        relative.visibility =View.GONE
                        bottom.visibility =View.GONE
                        submitanimation1.visibility = View.VISIBLE
                        val thread2: Thread = object : Thread() {
                            override fun run() {
                                try {
                                    sleep(7000)

                                    submitanimation1.playAnimation()

                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                finally{
                                    val u = Intent(applicationContext, MainActivity::class.java)
                                    //Toast.makeText(this@CartActivity,"Order Placed Successfully",Toast.LENGTH_SHORT).show()
                                    startActivity(u)

                                }
                            }
                        }
                        thread2.start()

                    },1)
                }else
                {
                    Toast.makeText(this@CartActivity,"Cart is Empty",Toast.LENGTH_SHORT).show()
                }

            }

        })


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
            val swipegesture = object: SwipeGesture(this@CartActivity)
            {
                @RequiresApi(Build.VERSION_CODES.N)
                @SuppressLint("LongLogTag")
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when(direction)
                    {
                        ItemTouchHelper.RIGHT->{


                            var dialog = Dialog(this@CartActivity)
                            dialog.setContentView(R.layout.customedialog)
                            dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.drawable))
                            dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                            dialog.setCancelable(false)

                            dialog.show()

                            dialog.okbuttonanother.setOnClickListener( object  :View.OnClickListener{

                                override fun onClick(p0: View?) {
                                    dialog.dismiss()

                                    GlobalScope.launch(Dispatchers.IO)
                                    {


                                        var willbedeleted = cartadapter.returnproduct(viewHolder.adapterPosition)

                                        deletedata(this@CartActivity, willbedeleted)

                                    }
//
                                }
                            })

                            dialog.cancelbutton.setOnClickListener( object  :View.OnClickListener{

                                override fun onClick(p0: View?) {
                                    dialog.dismiss()
                                    calldata(context)
                                }
                            })
                        }
                    }

                }

                private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_del)!!
                private val intrinsicWidth = deleteIcon.intrinsicWidth
                private val intrinsicHeight = deleteIcon.intrinsicHeight
                private val background = ColorDrawable()
                private val backgroundColor = Color.parseColor("#ff0000")
                private val clearPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) }
                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {

                    val itemView = viewHolder.itemView
                    val itemHeight = itemView.bottom - itemView.top

                    //Draw the red delete background

                    background.color = backgroundColor
                    background.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                    background.draw(c)

                    //Calculate the position of the delete icon

                    val deleteIconMargin = (itemHeight)
                    val deleteIconTop = itemView.top + deleteIconMargin
                    val deleteIconLeft = itemView.left
                    val deleteIconRight = itemView.right+100
                    val deleteIconBottom = itemView.bottom

                    deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
                    deleteIcon.draw(c)

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }



            }
            val touchHelper = ItemTouchHelper(swipegesture)
            touchHelper.attachToRecyclerView(cartrecycleview)






        }

    }

    private fun initiateViewmodel()
    {

        cartpageViewmodel =  ViewModelProvider(this).get(CartViewModel::class.java)
        cartpageViewmodel.getCartProductsLiveDataobserver().observe( this,{


            if(it!=null)
            {
                var sum = 0.0
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


        val handler = Handler(Looper.getMainLooper())
//        handler.post({
//            var tmp1= totalprice.text.toString().toFloat();
//            var tmp2 = cartProduct.price!!.toFloat()
//
//            var tmp3 = tmp1 - tmp2
//            Log.d("Tmp1:", tmp1.toString())
//            Log.d("Tmp2:", tmp2.toString())
//            Log.d("Tmp3:", tmp3.toString())
//
//            //totalprice.text = (tmp3).toString()
//        })

        cartpageViewmodel.deleteProductList(context,cartProduct)



        calldata(context)







    }


}


