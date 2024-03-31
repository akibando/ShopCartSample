package com.meliuscreation.sample.shopcartsample.data.repository

import com.meliuscreation.sample.shopcartsample.data.api.ProductApi
import com.meliuscreation.sample.shopcartsample.data.entities.ProductData


class DummyWebApiProductDataSource(private val api: ProductApi) : ProductDataSource.Remote {
    override suspend fun getProducts(): List<ProductData> {
         return api.getProducts()
    }
}