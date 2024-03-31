package com.meliuscreation.sample.shopcartsample.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.meliuscreation.sample.shopcartsample.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Simulate some loading time
    LaunchedEffect(true) {
        delay(1000)
        navController.navigate("home")
    }

    Surface(color = Color(0xFF3E9B3E)) { // Light green color
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Splash Image",
                modifier = Modifier
                    .width(290.dp)
                    .height(320.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Sample App",
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}