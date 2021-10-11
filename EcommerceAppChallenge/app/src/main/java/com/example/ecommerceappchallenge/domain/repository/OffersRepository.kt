package com.example.ecommerceappchallenge.domain.repository

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.repository.dataSource.OffersLocalDataSource

interface OffersRepository {
    suspend fun insertOffer(productsItem: ProductsItem)
    suspend fun deleteAll()
    fun getOffer(): LiveData<ProductsItem>
}