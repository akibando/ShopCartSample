package com.meliuscreation.sample.shopcartsample

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.meliuscreation.sample.shopcartsample.domain.usecase.AddCartItemUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetCartItemsUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetProductItemsUseCase
import com.meliuscreation.sample.shopcartsample.ui.ProductViewModel
import com.meliuscreation.sample.shopcartsample.ui.screen.HomeScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockRepository = MockProductRepository()
    private val getCartItemsUseCase = GetCartItemsUseCase(mockRepository)
    private val getProductItemsUseCase = GetProductItemsUseCase(mockRepository)
    private val addProductUseCase = AddCartItemUseCase(mockRepository)

    private lateinit var viewModel: ProductViewModel

    @Before
    fun setUp() {
        viewModel = ProductViewModel(getCartItemsUseCase, getProductItemsUseCase, addProductUseCase)
    }

    @Test
    fun `test home screen`() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        composeTestRule.setContent {
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            HomeScreen(navController, viewModel)
        }

        composeTestRule.onNodeWithText("商品一覧").assertExists()

        composeTestRule.onNodeWithContentDescription("shopping cart").performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "ShoppingCart")

    }
}