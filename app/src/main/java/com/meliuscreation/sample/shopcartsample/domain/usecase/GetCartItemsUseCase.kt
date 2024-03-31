package com.meliuscreation.sample.shopcartsample.domain.usecase

import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCartItemsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): List<CartItemEntity> {
        return withContext(Dispatchers.IO) { // IOスレッドでデータベース操作を行う
            productRepository.getCartItems()
        }
    }
}
