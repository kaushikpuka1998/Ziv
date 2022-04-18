package com.kgstrivers.ziv.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase;
import com.kgstrivers.ziv.Model.CartProduct
import com.kgstrivers.ziv.NetworkInterface.ProductDAO


@Database(entities = [CartProduct::class], version = 2, exportSchema = false)
abstract class Cartprod : RoomDatabase()  {

    abstract fun prodDao() : ProductDAO

    companion object{
        var instance:Cartprod?=null

        fun getInstance(context: Context):Cartprod
        {
            if(instance == null)
            {
                instance = Room.databaseBuilder(context.applicationContext,Cartprod::class.java,"cartProduct.db").build()
            }

            return instance!!
        }
    }


}