package com.kgstrivers.ziv.Model

import androidx.room.Entity
import androidx.room.PrimaryKey



    @Entity(tableName = "cartProducts")
    data class CartProduct(

        @PrimaryKey(autoGenerate = true)
        val uid:Int?,
        val image_url: String?,
        val name: String?,
        val price: String?,
        val rating: Int?
    )
