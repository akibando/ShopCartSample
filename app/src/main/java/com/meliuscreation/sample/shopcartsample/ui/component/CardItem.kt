package com.meliuscreation.sample.shopcartsample.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meliuscreation.sample.shopcartsample.R
import com.meliuscreation.sample.shopcartsample.ui.ProductViewModel

@Composable
fun CardItemWithState(productItemsUiState: ProductViewModel.ProductItemsUiState, onItemClick: () -> Unit) {
    val isEnabled = !(productItemsUiState.availability.equals("out-of-stock", ignoreCase = true)
            || productItemsUiState.availability.equals("coming-soon", ignoreCase = true))

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(80.dp)
            .clickable(enabled = isEnabled) {
                onItemClick()
            },

        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = if (productItemsUiState.image != null) {
                    BitmapPainter(image = productItemsUiState.image.asImageBitmap())
                } else {
                    painterResource(id = R.drawable.no_image)
                },
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = productItemsUiState.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = productItemsUiState.content,
                    style = TextStyle(fontSize = 10.sp),
                    modifier = Modifier.width(180.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .padding(end = 34.dp),
                contentAlignment = Alignment.Center
            ) {
                val circleColor = when (productItemsUiState.availability) {
                    "coming-soon" -> Color.Blue
                    "available" -> Color.Green
                    "out-of-stock" -> Color.Red
                    else -> Color.Gray
                }
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(circleColor, shape = CircleShape)
                )
            }
        }
    }
}

@Composable
fun CardItem(item: ProductViewModel.CartItemsUiState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(80.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        border = BorderStroke(0.1.dp, Color.Gray),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = if (item.image != null) {
                    BitmapPainter(image = item.image.asImageBitmap())
                } else {
                    painterResource(id = R.drawable.no_image)
                },
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = item.title, fontWeight = FontWeight.Bold)
        }
    }
}

