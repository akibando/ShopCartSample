package com.meliuscreation.sample.shopcartsample.data.repository

import com.meliuscreation.sample.shopcartsample.data.entities.ProductData
import com.meliuscreation.sample.shopcartsample.data.entities.ProductDbData

class ProductDataSource {
    interface Remote {
        suspend fun getProducts(): List<ProductData>

    }

    interface Local {
        suspend fun getProducts(): List<ProductDbData>
        suspend fun insertProduct(product: ProductDbData)
    }
}