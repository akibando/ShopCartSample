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
import com.meliuscreation.sample.shopcartsample.ui.screen.HomeScreen
import com.meliuscreation.sample.shopcartsample.ui.screen.ItemDetailScreen
import com.meliuscreation.sample.shopcartsample.ui.ProductViewModel
import com.meliuscreation.sample.shopcartsample.ui.screen.ShoppingCartScreen
import com.meliuscreation.sample.shopcartsample.ui.screen.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShopCartSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val productViewModel = hiltViewModel<ProductViewModel>()
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


