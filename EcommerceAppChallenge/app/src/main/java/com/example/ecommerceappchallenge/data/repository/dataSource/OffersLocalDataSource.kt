package com.example.ecommerceappchallenge.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.model.ProductsItem

interface OffersLocalDataSource {
    suspend fun insertOffer(productsItem: ProductsItem)
    suspend fun deleteAll()
    fun getOffer(): LiveData<ProductsItem>
}