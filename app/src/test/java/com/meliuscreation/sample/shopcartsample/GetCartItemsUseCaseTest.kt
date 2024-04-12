package com.meliuscreation.sample.shopcartsample

import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetCartItemsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCartItemsUseCaseTest {
    @Test
    fun `get cart items returns list of cart items`() = runBlocking {
        val mockProductRepository = mock<ProductRepository>()
        val getCartItemsUseCase = GetCartItemsUseCase(mockProductRepository)
        val cartItemEntity = CartItemEntity(imageUrl = "url 01", name = "item 01")
        val expectedCartItems = listOf(cartItemEntity)

        whenever(mockProductRepository.getCartItems()).thenReturn(expectedCartItems)

        val result = getCartItemsUseCase()

        assertEquals(expectedCartItems, result)
    }
}