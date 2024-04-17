package com.meliuscreation.sample.shopcartsample.ui.screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.meliuscreation.sample.shopcartsample.ui.component.ItemStateCardList
import com.meliuscreation.sample.shopcartsample.ui.component.MainAppBar
import com.meliuscreation.sample.shopcartsample.ui.ProductViewModel

@Composable
fun HomeScreen(navController: NavController, productViewModel: ProductViewModel) {

    val activity = LocalContext.current as Activity

    BackHandler {
        activity.finish()
    }

    Column {
        MainAppBar(
            title = "商品一覧",
            action = true,
            actionIcon = Icons.Filled.ShoppingCart,
            contentDescription = "shopping cart",
            onActionClick = {
                navController.navigate("ShoppingCart")
            })

        ItemStateCardList(navController, productViewModel)
    }
}