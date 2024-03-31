package com.meliuscreation.sample.shopcartsample.domain.entities

import android.graphics.Bitmap

data class CartItemEntity(
    val image: Bitmap? = null,
    val imageUrl: String = "",
    val name: String = ""
)
