package com.meliuscreation.sample.shopcartsample.data.repository

import android.graphics.Bitmap

interface ImageDataSource {
    interface Remote {
        suspend fun loadImage(url: String): Result<Bitmap>
    }
}