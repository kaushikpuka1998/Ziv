package com.kgstrivers.ziv.NetworkInterface

import androidx.room.*
import androidx.room.Update
import com.kgstrivers.ziv.Model.CartProduct
import com.kgstrivers.ziv.Model.Product


@Dao
interface ProductDAO {

    @Insert
    suspend fun insert(product: CartProduct)

    @Query("DELETE FROM cartProducts WHERE uid =:uid")
    suspend fun delete(uid:Int)


    @Update
    suspend fun update(product: CartProduct)


    @Query("SELECT * from cartProducts")
    suspend fun display():List<CartProduct>

}