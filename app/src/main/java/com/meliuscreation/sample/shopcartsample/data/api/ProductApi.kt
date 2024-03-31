package com.meliuscreation.sample.shopcartsample.data.api

import com.meliuscreation.sample.shopcartsample.data.entities.ProductData

interface ProductApi {
    suspend fun getProducts(): List<ProductData>
}