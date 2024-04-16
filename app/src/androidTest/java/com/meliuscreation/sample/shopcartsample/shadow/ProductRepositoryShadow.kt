package com.meliuscreation.sample.shopcartsample.shadow

import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements


@Implements(ProductRepository::class)
class ProductRepositoryShadow :  ProductRepository {

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

    @Implementation
    fun replaceAllProductItem(productItemList: List<ProductDataStateEntity>) {
        productItems.clear()
        productItems.addAll(productItemList)
    }
    @Implementation
    fun replaceAllCartItem(cartItemList: List<CartItemEntity>) {
        cartItems.clear()
        cartItems.addAll(cartItemList)
    }

}