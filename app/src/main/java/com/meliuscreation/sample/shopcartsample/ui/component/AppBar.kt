package com.meliuscreation.sample.shopcartsample.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(title: String, arrowBack: Boolean = false, action: Boolean = false,
               actionIcon: ImageVector? = null, contentDescription: String, onActionClick: (() -> Unit)? = null,
               onArrowBackClick: (() -> Unit)? = null) {
    TopAppBar(
        title = {
            Row {
                Text(
                    text = title,
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        },
        actions = {
            if (action && actionIcon != null && onActionClick != null) {
                IconButton(onClick = { onActionClick() }) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = contentDescription,
                    )
                }
            }
        },
        navigationIcon = {
            if (arrowBack && onArrowBackClick != null) {
                IconButton(onClick = { onArrowBackClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
    )
}
