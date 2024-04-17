package com.meliuscreation.sample.shopcartsample

import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity



class MockProductRepository :  ProductRepository {

    private var cartItems: MutableList<CartItemEntity> = mutableListOf()
    private var productItems: MutableList<ProductDataStateEntity> = mutableListOf()

    override suspend fun getCartItems(): List<CartItemEntity> {
        return cartItems
    }

    override suspend fun addCartItem(cartItemEntity: CartItemEntity) {
        cartItems.add(cartItemEntity)
    }

    override suspend fun getProductItems(): List<ProductDataStateEntity> {
        return productItems
    }
}