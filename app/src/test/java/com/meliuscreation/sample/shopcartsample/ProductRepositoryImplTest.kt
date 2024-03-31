package com.meliuscreation.sample.shopcartsample

import android.graphics.Bitmap
import com.meliuscreation.sample.shopcartsample.data.entities.ProductDbData
import com.meliuscreation.sample.shopcartsample.data.entities.ProductData
import com.meliuscreation.sample.shopcartsample.data.repository.ImageDataSource
import com.meliuscreation.sample.shopcartsample.data.repository.ProductDataSource
import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepositoryImpl
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ProductRepositoryImplTest {

    private lateinit var repository: ProductRepositoryImpl
    private lateinit var localDataSource: ProductDataSource.Local
    private lateinit var remoteDataSource: ProductDataSource.Remote
    private lateinit var remoteImageDataSource: ImageDataSource.Remote

    @Before
    fun setUp() {
        localDataSource = mock()
        remoteDataSource = mock()
        remoteImageDataSource = mock()
        repository = ProductRepositoryImpl(localDataSource, remoteDataSource, remoteImageDataSource)
    }

    @Test
    fun `test getCartItems`() = runBlocking {
        // Mock data
        val dbDataList = listOf(ProductDbData(1, "image_url_1", "product_name_1"))
        val bitmap = mock<Bitmap>()
        val cartItemEntity = CartItemEntity(bitmap, "image_url_1", "product_name_1")
        val bitmapResult = Result.success(bitmap)

        // Stubbing
        whenever(localDataSource.getProducts()).thenReturn(dbDataList)
        whenever(remoteImageDataSource.loadImage(any())).thenReturn(bitmapResult)

        // Call method under test
        val result = repository.getCartItems()

        // Verify
        verify(localDataSource).getProducts()
        verify(remoteImageDataSource).loadImage("image_url_1")
        assertEquals(listOf(cartItemEntity), result)
    }

    @Test
    fun `test addCartItem`() = runBlocking {
        // Mock data
        val cartItemEntity = CartItemEntity(null,"image_url_1", "product_name_1")

        // Call method under test
        repository.addCartItem(cartItemEntity)

        // Verify
        verify(localDataSource).insertProduct(ProductDbData(0, "image_url_1", "product_name_1"))
    }

    @Test
    fun `test getProductItems`() = runBlocking {
        // Mock data
        val productDataList = listOf(ProductData("image_url_1", "product_name_1", "", 0,""))
        val bitmap = mock<Bitmap>()
        val productDataStateEntity = ProductDataStateEntity(bitmap, "image_url_1", "product_name_1")
        val bitmapResult = Result.success(bitmap)

        // Stubbing
        whenever(remoteDataSource.getProducts()).thenReturn(productDataList)
        whenever(remoteImageDataSource.loadImage(any())).thenReturn(bitmapResult)

        // Call method under test
        val result = repository.getProductItems()

        // Verify
        verify(remoteDataSource).getProducts()
        verify(remoteImageDataSource).loadImage("image_url_1")
        assertEquals(listOf(productDataStateEntity), result)
    }
}
