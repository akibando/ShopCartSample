package com.meliuscreation.sample.shopcartsample.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException
import java.net.URL
import kotlin.Result

class DummyWebImageDataSource : ImageDataSource.Remote {
    override suspend fun loadImage(url: String): Result<Bitmap> {
        return try {
            val inputStream = URL(url).openStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            Result.success(bitmap)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}