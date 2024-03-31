package com.meliuscreation.sample.shopcartsample.domain.usecase

import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddCartItemUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(product: CartItemEntity) {
        return withContext(Dispatchers.IO) { // IOスレッドでデータベース操作を行う
            productRepository.addCartItem(product)
        }
    }
}