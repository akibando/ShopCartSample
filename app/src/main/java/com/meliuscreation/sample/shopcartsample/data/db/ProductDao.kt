package com.meliuscreation.sample.shopcartsample.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.meliuscreation.sample.shopcartsample.data.entities.ProductDbData

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAllProducts(): List<ProductDbData>

    @Insert
    fun insertProduct(product: ProductDbData)
}