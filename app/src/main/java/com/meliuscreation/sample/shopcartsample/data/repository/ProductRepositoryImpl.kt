package com.meliuscreation.sample.shopcartsample.data.repository

import com.meliuscreation.sample.shopcartsample.data.entities.ProductDbData
import com.meliuscreation.sample.shopcartsample.data.entities.toDomainEntity
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity

class ProductRepositoryImpl(
    private val localDataSource: ProductDataSource.Local,
    private val remoteDataSource: ProductDataSource.Remote,
    private val remoteImageDataSource: ImageDataSource.Remote
): ProductRepository  {

    override suspend fun getCartItems(): List<CartItemEntity> {
        val cartItemsDdData = localDataSource.getProducts()

        val cartItemEntityList = mutableListOf<CartItemEntity>()

        for (dbData in cartItemsDdData) {
            val bitmapResult  = remoteImageDataSource.loadImage(dbData.imageUrl)
            val productEntity = if (bitmapResult.isSuccess) {
                dbData.toDomainEntity().copy(image = bitmapResult.getOrNull())

            } else {
                dbData.toDomainEntity().copy(image = null)
            }
            cartItemEntityList.add(productEntity)
        }

        return cartItemEntityList
    }

    override suspend fun addCartItem(cartItemEntity: CartItemEntity) {
        localDataSource.insertProduct(ProductDbData(
            imageUrl = cartItemEntity.imageUrl,
            name = cartItemEntity.name
        ))
    }

    override suspend fun getProductItems(): List<ProductDataStateEntity> {
        val productDataList = remoteDataSource.getProducts()

        val productDataStateEntityList = mutableListOf<ProductDataStateEntity>()

        for (productData in productDataList) {
            val bitmapResult  = remoteImageDataSource.loadImage(productData.imageURL)
            val productStateEntity = if (bitmapResult.isSuccess) {
                productData.toDomainEntity().copy(image = bitmapResult.getOrNull())

            } else {
                productData.toDomainEntity().copy(image = null)
            }
            productDataStateEntityList.add(productStateEntity)
        }

        return productDataStateEntityList
    }
}