package com.meliuscreation.sample.shopcartsample

import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.usecase.AddCartItemUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AddCartItemUseCaseTest {

    @Test
    fun `add cart item invokes repository`() = runBlocking {
        val mockProductRepository = mock<ProductRepository>()
        val addCartItemUseCase = AddCartItemUseCase(mockProductRepository)
        val testCartItem = CartItemEntity(/* mock your CartItemEntity */)

        addCartItemUseCase(testCartItem)

        verify(mockProductRepository).addCartItem(testCartItem)
    }
}