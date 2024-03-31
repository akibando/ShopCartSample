package com.meliuscreation.sample.shopcartsample.domain.usecase

import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProductItemsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): List<ProductDataStateEntity> {
        return withContext(Dispatchers.IO) {
            productRepository.getProductItems()
        }
    }
}