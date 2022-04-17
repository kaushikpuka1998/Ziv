package com.kgstrivers.ziv.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable




data class Product(
    val image_url: String,
    val name: String,
    val price: String,
    val rating: Int
)