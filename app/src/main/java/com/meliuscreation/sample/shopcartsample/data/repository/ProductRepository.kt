package com.meliuscreation.sample.shopcartsample.data.repository

import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity

interface ProductRepository {
    suspend fun getCartItems(): List<CartItemEntity>
    suspend fun addCartItem(cartItemEntity: CartItemEntity)
    suspend fun getProductItems(): List<ProductDataStateEntity>

}