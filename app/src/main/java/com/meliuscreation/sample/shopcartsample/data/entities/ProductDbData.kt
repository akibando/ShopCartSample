package com.meliuscreation.sample.shopcartsample.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.meliuscreation.sample.shopcartsample.domain.entities.CartItemEntity

@Entity(tableName = "product")
data class ProductDbData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUrl: String,
    val name: String
)
fun ProductDbData.toDomainEntity() = CartItemEntity(
    image = null,
    imageUrl = imageUrl,
    name = name
)
