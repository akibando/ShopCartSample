package com.meliuscreation.sample.shopcartsample.data.api

import android.content.Context
import com.meliuscreation.sample.shopcartsample.R
import com.meliuscreation.sample.shopcartsample.data.entities.ProductData
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader


class DummyWebApi(private val context: Context): ProductApi {
    override suspend fun getProducts(): List<ProductData> {
        val inputStream = context.resources.openRawResource(R.raw.dummy_webapi)
        val jsonStringBuilder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            jsonStringBuilder.append(line)
        }

        val json = jsonStringBuilder.toString()

        val productStateEntityList = mutableListOf<ProductData>()
        val jsonObject = JSONObject(json)
        val itemsArray = jsonObject.getJSONArray("items")
        for (i in 0 until itemsArray.length()) {
            val itemObject = itemsArray.getJSONObject(i)
            val productData = ProductData(
                imageURL = itemObject.getString("imageURL"),
                name = itemObject.getString("name"),
                content = itemObject.getString("content"),
                price = itemObject.getInt("price"),
                status = itemObject.getString("status")
            )
            productStateEntityList.add(productData)
        }

        return productStateEntityList
    }
}