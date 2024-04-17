package com.meliuscreation.sample.shopcartsample.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.meliuscreation.sample.shopcartsample.ui.component.ItemCardList
import com.meliuscreation.sample.shopcartsample.ui.component.MainAppBar
import com.meliuscreation.sample.shopcartsample.ui.ProductViewModel

@Composable
fun ShoppingCartScreen(navController: NavController, productViewModel: ProductViewModel) {
    Column {
        MainAppBar(title = "商品カート",
            arrowBack = true,
            contentDescription = "arrow back click",
            onArrowBackClick = { navController.popBackStack() })
        ItemCardList(productViewModel)
    }
}