package com.example.ecommerceappchallenge.view.util

import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.google.gson.Gson

class ProductsItemConverter {
    companion object {
        fun serialize(product: ProductsItem): String {
            return Gson().toJson(product)
        }

        fun deserialize(jsonString: String): ProductsItem {
            return Gson().fromJson(jsonString,ProductsItem::class.java)
        }
    }
}