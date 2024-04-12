package com.meliuscreation.sample.shopcartsample

import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetProductItemsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetProductItemsUseCaseTest {
    @Test
    fun `get product items returns list of product items`() = runBlocking {
        // Given
        val mockProductRepository = mock<ProductRepository>()
        val getProductItemsUseCase = GetProductItemsUseCase(mockProductRepository)
        val productDataStateEntity = ProductDataStateEntity(
            imageUrl = "url 01",
            name = "name 01",
            content = "content 01",
            price = 1000,
            status = "fake status")
        val expectedProductItems = listOf(productDataStateEntity)

        whenever(mockProductRepository.getProductItems()).thenReturn(expectedProductItems)

        val result = runBlocking { getProductItemsUseCase() }

        assertEquals(expectedProductItems, result)
    }
}