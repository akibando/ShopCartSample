package com.meliuscreation.sample.shopcartsample.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.meliuscreation.sample.shopcartsample.ui.ProductViewModel

@Composable
fun ItemStateCardList(navController: NavController, productViewModel: ProductViewModel) {
    LaunchedEffect(true) {
        productViewModel.onInitialProductItemsState()
    }

    val productItemLiveData by productViewModel.getProductItems().observeAsState(initial = emptyList())
    productItemLiveData.let { productItems ->
        LazyColumn(modifier = Modifier
            .padding(vertical = 16.dp)
        ) {
            items(productItems) { item ->
                CardItemWithState(item) {
                    navController.navigate("itemDetail/${item.title}")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ItemCardList(productViewModel: ProductViewModel) {
    LaunchedEffect(true) {
        productViewModel.onInitialCartItemsState()
    }
    val cartItemLiveData by productViewModel.getCartItems().observeAsState(initial = emptyList())
    cartItemLiveData.let { cartItems ->
        LazyColumn(modifier = Modifier
            .padding(vertical = 16.dp)
        ) {
            items(cartItems) { item ->
                CardItem(item)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

//@Composable
//fun ItemCardList(productViewModel: ProductViewModel) {
//    val cartItemsUiState by remember { productViewModel.onInitialCartItemsState() }.observeAsState()
//    cartItemsUiState?.let { cartItems ->
//        LazyColumn(modifier = Modifier
//            .padding(vertical = 16.dp)
//        ) {
//            items(cartItems) { item ->
//                CardItem(item)
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//    }
//}
