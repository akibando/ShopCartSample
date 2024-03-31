package com.meliuscreation.sample.shopcartsample.domain.entities

import android.graphics.Bitmap

data class ProductDataStateEntity(
    val image: Bitmap? = null,
    val imageUrl: String = "",
    val name: String = "",
    val content: String = "",
    val price: Int = 0,
    val status: String = ""
)

fun ProductDataStateEntity.isEmpty(): Boolean {
    return (image == null && name == "" && content == "" && price == 0 && status == "")
}
