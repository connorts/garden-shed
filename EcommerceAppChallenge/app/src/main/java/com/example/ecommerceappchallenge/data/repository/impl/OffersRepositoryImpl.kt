package com.example.ecommerceappchallenge.data.repository.impl

import androidx.lifecycle.LiveData
import com.example.ecommerceappchallenge.data.model.ProductsItem
import com.example.ecommerceappchallenge.data.repository.dataSource.OffersLocalDataSource
import com.example.ecommerceappchallenge.domain.repository.OffersRepository

class OffersRepositoryImpl(private val offersLocalDataSource: OffersLocalDataSource): OffersRepository {
    override suspend fun insertOffer(productsItem: ProductsItem) {
        return offersLocalDataSource.insertOffer(productsItem)
    }

    override suspend fun deleteAll() {
        return offersLocalDataSource.deleteAll()
    }

    override fun getOffer(): LiveData<ProductsItem> {
        return offersLocalDataSource.getOffer()
    }
}