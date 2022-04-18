package com.kgstrivers.ziv.NetworkInterface

import androidx.room.*
import androidx.room.Update
import com.kgstrivers.ziv.Model.CartProduct
import com.kgstrivers.ziv.Model.Product


@Dao
interface ProductDAO {

    @Insert
    suspend fun insert(product: CartProduct)

    @Delete
    suspend fun delete(cartProduct: CartProduct)


    @Query("Delete  from cartProducts")
    suspend fun deleteAll()


    @Update
    suspend fun update(product: CartProduct)


    @Query("SELECT * from cartProducts")
    suspend fun display():List<CartProduct>

    @Query("SELECT EXISTS (SELECT 1 FROM cartProducts WHERE image_url= :image_url)")
    suspend fun isproductAdded(image_url: String): Int

}