package com.meliuscreation.sample.shopcartsample.data.repository

import com.meliuscreation.sample.shopcartsample.data.db.ProductDao
import com.meliuscreation.sample.shopcartsample.data.entities.ProductDbData

class RoomProductDataSource(private val productDao: ProductDao) : ProductDataSource.Local {
    override suspend fun getProducts(): List<ProductDbData> {
        return productDao.getAllProducts()
    }

    override suspend fun insertProduct(product: ProductDbData) {
        productDao.insertProduct(product)
    }
}