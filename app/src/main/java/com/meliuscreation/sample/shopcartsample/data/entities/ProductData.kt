package com.meliuscreation.sample.shopcartsample.data.entities

import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity

data class ProductData(
    val imageURL: String,
    val name: String,
    val content: String,
    val price: Int,
    val status: String
)

fun ProductData.toDomainEntity() = ProductDataStateEntity(
    image = null,
    imageUrl = imageURL,
    name = name,
    content = content,
    price = price,
    status = status
)
