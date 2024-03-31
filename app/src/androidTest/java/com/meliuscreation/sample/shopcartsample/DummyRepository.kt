package com.meliuscreation.sample.shopcartsample

import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity

class DummyRepository(
    private val cartItems: List<CartItemEntity>,
    private val productItems: List<ProductDataStateEntity>
): ProductRepository {

    companion object {
        private var cartItemEntityStatic = CartItemEntity()
    }

    override suspend fun getCartItems(): List<CartItemEntity> {
        return cartItems
    }

    override suspend fun addCartItem(cartItemEntity: CartItemEntity) {
        cartItemEntityStatic = cartItemEntity.copy()
    }

    override suspend fun getProductItems(): List<ProductDataStateEntity> {
        return productItems
    }

    fun getCartItemEntity(): CartItemEntity {
        return cartItemEntityStatic
    }

}