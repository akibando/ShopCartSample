package com.meliuscreation.sample.shopcartsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.meliuscreation.sample.shopcartsample.ui.theme.ShopCartSampleTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.meliuscreation.sample.shopcartsample.data.api.DummyWebApi
import com.meliuscreation.sample.shopcartsample.data.db.AppDatabase
import com.meliuscreation.sample.shopcartsample.data.repository.DummyWebApiProductDataSource
import com.meliuscreation.sample.shopcartsample.data.repository.DummyWebImageDataSource
import com.meliuscreation.sample.shopcartsample.data.repository.ProductRepositoryImpl
import com.meliuscreation.sample.shopcartsample.data.repository.RoomProductDataSource
import com.meliuscreation.sample.shopcartsample.domain.usecase.AddCartItemUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetCartItemsUseCase
import com.meliuscreation.sample.shopcartsample.domain.usecase.GetProductItemsUseCase
import com.meliuscreation.sample.shopcartsample.ui.screen.HomeScreen
import com.meliuscreation.sample.shopcartsample.ui.screen.ItemDetailScreen
import com.meliuscreation.sample.shopcartsample.ui.ProductViewModel
import com.meliuscreation.sample.shopcartsample.ui.screen.ShoppingCartScreen
import com.meliuscreation.sample.shopcartsample.ui.screen.SplashScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productDao = AppDatabase.getDatabase(applicationContext).productDao()
        val roomProductDataSource = RoomProductDataSource(productDao)
        val productWebapi = DummyWebApi(this)
        val dummyWebApiProductDataSource = DummyWebApiProductDataSource(productWebapi)
        val dummyWebImageDataSource = DummyWebImageDataSource()
        val productRepository = ProductRepositoryImpl(roomProductDataSource, dummyWebApiProductDataSource, dummyWebImageDataSource)
        val getProductItemsUseCase = GetProductItemsUseCase(productRepository)
        val getCartItemsUseCase = GetCartItemsUseCase(productRepository)
        val addCartItemUseCase = AddCartItemUseCase(productRepository)
        val productViewModel = ProductViewModel(getCartItemsUseCase, getProductItemsUseCase, addCartItemUseCase)

        setContent {
            ShopCartSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "splash") {
                        composable("splash") {
                            SplashScreen(navController)
                        }
                        composable("Home") {
                            HomeScreen(navController = navController, productViewModel)
                        }
                        composable(
                            route = "itemDetail/{title}",
                            arguments = listOf(
                                navArgument("title") { type = NavType.StringType },
                            )
                        ) { backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title") ?: ""
                            ItemDetailScreen(navController, productViewModel, title)
                        }
                        composable("ShoppingCart") {
                            ShoppingCartScreen(navController = navController, productViewModel = productViewModel)
                        }
                    }
                }

            }
        }
    }
}


