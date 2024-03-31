package com.meliuscreation.sample.shopcartsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.Observer
import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepository
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity
import com.meliuscreation.sample.shopcartsample.domain.entities.ProductDataStateEntity
import com.meliuscreation.sample.shopcartsample.domain.usecase.AddCartItemUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetCartItemsUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetProductItemsUseCase
import com.meliuscreation.sample.shopcartsample.ui.ProductViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class ProductViewModelTest {


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val cartItems = listOf(
        CartItemEntity(imageUrl = "url1", name = "Cart Item 1"),
        CartItemEntity(imageUrl = "url2", name = "Cart Item 2")
    )

    private val productItems = listOf(
        ProductDataStateEntity(imageUrl = "url1", name = "Product 1"),
        ProductDataStateEntity(imageUrl = "url2", name = "Product 2")
    )

    private val dummyRepository = DummyRepository(cartItems,productItems)
    private val getCartItemsUseCase = GetCartItemsUseCase(dummyRepository)
    private val getProductItemsUseCase = GetProductItemsUseCase(dummyRepository)
    private val addProductUseCase = AddCartItemUseCase(dummyRepository)

    private lateinit var viewModel: ProductViewModel


    @Before
    fun setUp() {
        viewModel = ProductViewModel(getCartItemsUseCase, getProductItemsUseCase, addProductUseCase)
    }

    @Test
    fun `test initial product items state`() = runBlocking {

        viewModel.onInitialProductItemsState()

        delay(1000)

        val result = viewModel.getProductItems().value

        val productItemsUiStateList = productItems.map { item ->
            ProductViewModel.ProductItemsUiState(
                image = item.image,
                imageUrl = item.imageUrl,
                title = item.name,
                content = item.content,
                price = item.price,
                availability = item.status
            )}

        // Verify
        Assert.assertEquals(productItemsUiStateList, result)
    }


    @Test
    fun `test initial cart items state`() = runBlocking {

        viewModel.onInitialCartItemsState()

        delay(1000)

        val result = viewModel.getCartItems().value

        val cartItemsUiStateList = cartItems.map { item ->
            ProductViewModel.CartItemsUiState(
                image = item.image,
                title = item.name
            )}

        // Verify
        Assert.assertEquals(cartItemsUiStateList, result)
    }
}
