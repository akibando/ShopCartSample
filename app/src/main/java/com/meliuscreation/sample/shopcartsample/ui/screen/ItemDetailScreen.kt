package com.meliuscreation.sample.shopcartsample.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.meliuscreation.sample.shopcartsample.R
import com.meliuscreation.sample.shopcartsample.ui.component.MainAppBar
import com.meliuscreation.sample.shopcartsample.ui.ProductViewModel

@Composable
fun ItemDetailScreen(navController: NavController, productViewModel: ProductViewModel, title: String) {
    val productItemUiState = productViewModel.getProductItem(title)
    Column {
        MainAppBar(title = productItemUiState.title,
            arrowBack = true,
            onArrowBackClick = { navController.popBackStack() })
        ItemDetailContent(productItemUiState) {
            Log.v("ItemDetailScreen", "on add cart ${productItemUiState.title}")
            productViewModel.onAddCartItem(productItemUiState)
        }
    }
}
//fun CardItemWithState(product: ProductStateEntity, onItemClick: () -> Unit) {
@Composable
fun ItemDetailContent(productItem: ProductViewModel.ProductItemsUiState, onAddToCartClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = if (productItem.image != null) {
                BitmapPainter(image = productItem.image.asImageBitmap())
            } else {
                painterResource(id = R.drawable.no_image)
            },
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
        )
        Column(
            modifier = Modifier.padding(vertical = 6.dp)
        ) {
            Text(
                text = productItem.content,
                style = TextStyle(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "¥${productItem.price}(税込み)",
                style = TextStyle(fontSize = 30.sp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // カートに追加ボタン
        Button(
            onClick = { onAddToCartClick() },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
            )
        ) {
            Text(text = "カートに追加")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ItemDetailScreenPreview() {
//    ShopCartSampleTheme {
//        val navController = rememberNavController()
//        ItemDetailScreen(
//            navController = navController,
//            imageResource = R.drawable.negi_ramen,
//            title = "ラーメン",
//            content = "あいうえお",
//            price = "1,000円")
//    }
//}